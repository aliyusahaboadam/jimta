package academy.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import academy.utility.JwtUtil;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;
    
    // Define public endpoints that don't require authentication
    // Kept in sync with SecurityConfig's permitAll() list
    private final List<String> publicEndpoints = Arrays.asList(
        "/health",
        "/actuator/**",    // All actuator endpoints
        "/v1/api/login",
        "/v1/api/admin/add",
        "/v1/api/admin/exists-by-email/**"
    );
    
    public JwtAuthenticationFilter(HandlerExceptionResolver handlerExceptionResolver, 
                                 JwtUtil jwtUtil,
                                 UserDetailsService userDetailsService) {
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.objectMapper = new ObjectMapper();
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                  HttpServletResponse response, 
                                  FilterChain filterChain) throws ServletException, IOException {
        
        String requestPath = request.getRequestURI();
        String method = request.getMethod();
        
   
        
        // Add logging to debug
        System.out.println("JWT Filter - Processing: " + method + " " + requestPath);
        
        // Skip JWT processing for public endpoints
        if (isPublicEndpoint(requestPath)) {
            System.out.println("JWT Filter - Skipping authentication for public endpoint: " + requestPath);
            filterChain.doFilter(request, response);
            return;
        }
        
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        
        // Extract token from Authorization header
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }
        
        // Only proceed if we have a token
        if (token != null) {
            try {
                username = jwtUtil.extractUsername(token);
                
                // Check if user is not already authenticated
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                
                if (username != null && authentication == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    
                    // Validate token
                    if (jwtUtil.isTokenValid(token, userDetails)) {
                        
                        UsernamePasswordAuthenticationToken authToken = 
                            new UsernamePasswordAuthenticationToken(
                                userDetails, 
                                null, 
                                userDetails.getAuthorities()
                            );
                        
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
                
            } catch (ExpiredJwtException expiredEx) {
                System.out.println("JWT Token has expired: " + expiredEx.getMessage());
                SecurityContextHolder.clearContext();
                handleJwtException(response, "JWT_EXPIRED", "JWT token has expired. Please login again.");
                return;
                
            } catch (MalformedJwtException malformedEx) {
                System.out.println("JWT Token is malformed: " + malformedEx.getMessage());
                SecurityContextHolder.clearContext();
                handleJwtException(response, "JWT_MALFORMED", "Invalid JWT token format.");
                return;
                
            } catch (SignatureException signatureEx) {
                System.out.println("JWT Token signature is invalid: " + signatureEx.getMessage());
                SecurityContextHolder.clearContext();
                handleJwtException(response, "JWT_SIGNATURE_INVALID", "JWT token signature is invalid.");
                return;
                
            } catch (JwtException jwtEx) {
                System.out.println("JWT Token error: " + jwtEx.getMessage());
                SecurityContextHolder.clearContext();
                handleJwtException(response, "JWT_ERROR", "JWT token is invalid.");
                return;
                
            } catch (Exception exception) {
                // Log the exception for debugging
                System.out.println("JWT Filter Exception: " + exception.getMessage());
                exception.printStackTrace();
                
                // Clear security context on error
                SecurityContextHolder.clearContext();
                handleJwtException(response, "AUTHENTICATION_ERROR", "Authentication failed.");
                return;
            }  
        } else {
            // No token provided for protected endpoint
            System.out.println("No JWT token provided for protected endpoint: " + requestPath);
            handleJwtException(response, "NO_TOKEN", "JWT token is required for this endpoint.");
            return;
        }
        
        // Continue the filter chain only if no JWT errors occurred
        filterChain.doFilter(request, response);
    }
    
    /**
     * Handle JWT exceptions by sending appropriate HTTP response
     */
    private void handleJwtException(HttpServletResponse response, String errorCode, String message) 
            throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        
        // Create error response body
        ErrorResponse errorResponse = new ErrorResponse(
            errorCode,
            message,
            System.currentTimeMillis()
        );
        
        String jsonResponse = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(jsonResponse);
    }
    
    /**
     * Inner class for error response structure
     */
    public static class ErrorResponse {
        private String error;
        private String message;
        private long timestamp;
        
        public ErrorResponse(String error, String message, long timestamp) {
            this.error = error;
            this.message = message;
            this.timestamp = timestamp;
        }
        
        // Getters
        public String getError() { return error; }
        public String getMessage() { return message; }
        public long getTimestamp() { return timestamp; }
        
        // Setters
        public void setError(String error) { this.error = error; }
        public void setMessage(String message) { this.message = message; }
        public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    }
    
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestPath = request.getRequestURI();
        boolean isPublic = isPublicEndpoint(requestPath);
        
        if (isPublic) {
            System.out.println("JWT Filter - Skipping filter for public endpoint: " + requestPath);
        }
        
        return isPublic;
    }
    
    /**
     * Check if the request path is a public endpoint
     * Supports both exact matches and wildcard patterns
     */
    private boolean isPublicEndpoint(String requestPath) {
        return publicEndpoints.stream()
                .anyMatch(endpoint -> {
                    if (endpoint.endsWith("/**")) {
                        // Pattern matching for paths like "/api/public/**"
                        String basePath = endpoint.substring(0, endpoint.length() - 3);
                        return requestPath.startsWith(basePath);
                    } else {
                        // Exact match
                        return requestPath.equals(endpoint) || requestPath.startsWith(endpoint + "/");
                    }
                });
    }
}