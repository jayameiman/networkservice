package id.co.bni.qris.repository;

import id.co.bni.qris.domain.model.user.RefreshToken;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);
}