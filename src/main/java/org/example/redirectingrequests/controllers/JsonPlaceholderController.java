package org.example.redirectingrequests.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class JsonPlaceholderController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "https://jsonplaceholder.typicode.com";
    private final CacheManager cacheManager;

    @GetMapping("/posts/{id}")
    @Cacheable(value = "posts", key = "#id")
    public ResponseEntity<Object> getPostById(@PathVariable Long id) {
        log.info("Getting post by ID: {}", id);
        return getRequest("/posts/{id}", Object.class, id);
    }

    @PostMapping("/posts")
    public ResponseEntity<Object> createPost(@RequestBody Object post) {
        log.info("Creating new post: {}", post);
        return exchangeRequest("/posts", HttpMethod.POST, post, Object.class);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable Long id, @RequestBody Object updatedPost) {
        log.info("Updating post with ID {}: {}", id, updatedPost);
        return exchangeRequest("/posts/{id}", HttpMethod.PUT, updatedPost, Object.class, id);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable Long id) {
        log.info("Deleting post with ID: {}", id);
        return exchangeRequest("/posts/{id}", HttpMethod.DELETE, null, Object.class, id);
    }

    @Cacheable(cacheNames = "users", key = "#id")
    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        log.info("Fetching user with id {}", id);
        return getRequest("/users/{id}", Object.class, id);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody Object user) {
        log.info("Creating a new user");
        return exchangeRequest("/users", HttpMethod.POST, user, Object.class);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody Object updatedUser) {
        log.info("Updating user with id {}", id);
        return exchangeRequest("/users/{id}", HttpMethod.PUT, updatedUser, Object.class, id);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        log.info("Deleting user with id {}", id);
        return exchangeRequest("/users/{id}", HttpMethod.DELETE, null, Object.class, id);
    }

    @Cacheable(cacheNames = "albums", key = "#id")
    @GetMapping("/albums/{id}")
    public ResponseEntity<Object> getAlbumById(@PathVariable Long id) {
        log.info("Fetching album with id {}", id);
        return getRequest("/albums/{id}", Object.class, id);
    }

    @PostMapping("/albums")
    public ResponseEntity<Object> createAlbum(@RequestBody Object album) {
        return exchangeRequest("/albums", HttpMethod.POST, album, Object.class);
    }

    @PutMapping("/albums/{id}")
    public ResponseEntity<Object> updateAlbum(@PathVariable Long id, @RequestBody Object updatedAlbum) {
        log.info("Updating album with id {}", id);
        return exchangeRequest("/albums/{id}", HttpMethod.PUT, updatedAlbum, Object.class, id);
    }

    @DeleteMapping("/albums/{id}")
    public ResponseEntity<Object> deleteAlbum(@PathVariable Long id) {
        log.info("Deleting album with id {}", id);
        return exchangeRequest("/albums/{id}", HttpMethod.DELETE, null, Object.class, id);
    }

    private <T> ResponseEntity<T> getRequest(String endpoint, Class<T> responseType, Object... urlVariables) {
        return restTemplate.exchange(baseUrl + endpoint, HttpMethod.GET, null, responseType, urlVariables);
    }

    private <T> ResponseEntity<T> exchangeRequest(String endpoint, HttpMethod method, Object requestBody, Class<T> responseType, Object... urlVariables) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> request = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(baseUrl + endpoint, method, request, responseType, urlVariables);
    }
}