package com.zirubihara.phototraveller.phototraveller.service;

import com.zirubihara.phototraveller.phototraveller.repository.RefreshTokenRepository;
import com.zirubihara.phototraveller.phototraveller.exceptions.SpringPhotoTravellerException;
import com.zirubihara.phototraveller.phototraveller.model.RefreshToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

        return refreshTokenRepository.save(refreshToken);
    }

    void validateRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new SpringPhotoTravellerException("Niepoprawny refresh Token"));
    }

    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
