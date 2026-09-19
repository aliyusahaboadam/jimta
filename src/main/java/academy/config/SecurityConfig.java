package academy.config;

import academy.model.User;

import java.util.Arrays;

import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;

import academy.auth.CustomAccessDeniedHandler;
import academy.auth.CustomAuthenticationEntryPoint;
import academy.filter.JwtAuthenticationFilter;
import academy.auth.CustomUserDetailsService;
import academy.utility.JwtUtil;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	private HandlerExceptionResolver handlerExceptionResolver;

	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;

	@Autowired
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	private JwtUtil jwtUtil;
	private CustomUserDetailsService customUserDetailsService;




	public SecurityConfig(HandlerExceptionResolver handlerExceptionResolver, JwtUtil jwtUtil,
			CustomUserDetailsService customUserDetailsService) {
		super();
		this.handlerExceptionResolver = handlerExceptionResolver;
		this.jwtUtil = jwtUtil;
		this.customUserDetailsService = customUserDetailsService;
	}

	 @Bean
	 public static PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	 }

	 @Bean
	 public CorsConfigurationSource corsConfigurationSource() {
	     CorsConfiguration configuration = new CorsConfiguration();

	     // Use setAllowedOriginPatterns instead of addAllowedOrigin (supports wildcards)
	     // FIXED: this list was still the miqwii project's origins. The academy
	     // frontend uses import.meta.env (Vite), whose dev server defaults to
	     // port 5173, not 3000 - that mismatch is what was causing the CORS
	     // block. Added the Vite dev port and the Jimta production domain
	     // (ForgotPasswordController already builds email links to
	     // jimtafootballacademy.com, so the frontend must be served from there).
	     configuration.setAllowedOriginPatterns(Arrays.asList(
	         "http://localhost:3000",
	         "http://localhost:5173",
	         "https://jimtafootballacademy.com",
	         "https://www.jimtafootballacademy.com",
	         "https://*.jimtafootballacademy.com"   // covers all subdomains
	     ));

	     configuration.addAllowedMethod("*");
	     configuration.addAllowedHeader("*");
	     configuration.setAllowCredentials(true);
	     configuration.addExposedHeader("Authorization");

	     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	     source.registerCorsConfiguration("/**", configuration);
	     return source;
	 }



	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         //
		http.cors(c -> c.configurationSource(corsConfigurationSource())).csrf(c -> c.disable())


		 .authorizeHttpRequests(authz -> authz


				// PERMIT ALL FIRST - must be at the top
				    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				    .requestMatchers(
				        "/health",
				        "/actuator/**",
				        "/v1/api/login",
				        "/v1/api/admin/add",
				        "/v1/api/admin/exists-by-email/**"
				    ).permitAll()


				 // ADMIN ONLY - full administrative access (roster management,
				 // creating coaches/players/teams/matches, recording performances,
				 // and raw user lookups since UserController exposes User directly)
	                .requestMatchers(
	                    "/v1/api/admin/get-all",
	                    "/v1/api/admin/get-by-id/**",
	                    "/v1/api/admin/get-authenticated-admin",
	                    "/v1/api/admin/welcome",
	                    "/v1/api/admin/delete/**",
	                    "/v1/api/coach/add",
	                    "/v1/api/coach/delete/**",
	                    "/v1/api/coach/get-all",
	                    "/v1/api/player/add",
	                    "/v1/api/player/update/**",
	                    "/v1/api/player/delete/**",
	                    "/v1/api/player/get-all",
	                    "/v1/api/team/add",
	                    "/v1/api/match/add",
	                    "/v1/api/performance/add",
	                    "/v1/api/user/get-by-id/**",
	                    "/v1/api/user/get-by-username/**",
	                    "/v1/api/user/get-by-role/**"
	                ).hasAuthority("ADMIN")

	                // ADMIN + COACH - coach-facing lookups an admin also needs
	                .requestMatchers(
	                    "/v1/api/coach/get-by-id/**",
	                    "/v1/api/coach/get-by-license/**",
	                    "/v1/api/coach/get-by-team/**"
	                ).hasAnyAuthority("ADMIN", "COACH")

	                // COACH ONLY - a coach's own identity
	                .requestMatchers(
	                    "/v1/api/coach/welcome",
	                    "/v1/api/coach/get-authenticated-coach"
	                ).hasAuthority("COACH")

	                // PLAYER ONLY - a player's own identity
	                .requestMatchers(
	                    "/v1/api/player/welcome",
	                    "/v1/api/player/get-authenticated-player"
	                ).hasAuthority("PLAYER")

	                // SHARED READ ACCESS - roster, fixtures, stats and profiles
	                // any logged-in admin/coach/player can view
	                .requestMatchers(
	                    "/v1/api/team/get-by-id/**",
	                    "/v1/api/team/get-all",
	                    "/v1/api/team/get-all-with-player-count",
	                    "/v1/api/team/get-by-coach/**",
	                    "/v1/api/team/get-by-age-group/**",
	                    "/v1/api/match/get-by-id/**",
	                    "/v1/api/match/get-all",
	                    "/v1/api/match/get-by-status/**",
	                    "/v1/api/match/get-by-team/**",
	                    "/v1/api/player/get-by-id/**",
	                    "/v1/api/player/get-profile/**",
	                    "/v1/api/player/get-by-team/**",
	                    "/v1/api/player/get-by-jersey",
	                    "/v1/api/performance/get-by-match/**",
	                    "/v1/api/performance/get-by-player/**",
	                    "/v1/api/performance/get-by-player-and-match",
	                    "/v1/api/performance/get-season-totals/**",
	                    "/v1/api/profile/get-by-player/**",
	                    "/v1/api/profile/get-by-coach/**",
	                    "/v1/api/profile/get-by-admin/**",
	                    "/v1/api/user/get-authenticated-user"
	                ).hasAnyAuthority("ADMIN", "COACH", "PLAYER")


	                // Deny all other requests
	                .anyRequest().authenticated()

				).exceptionHandling(ex -> ex
						.authenticationEntryPoint(customAuthenticationEntryPoint)
		                .accessDeniedHandler(customAccessDeniedHandler))
		 .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		       http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class );
		       	return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());

	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
	    return new JwtAuthenticationFilter(handlerExceptionResolver, jwtUtil, customUserDetailsService);
	}



}