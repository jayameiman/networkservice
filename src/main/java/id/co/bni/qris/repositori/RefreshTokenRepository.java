package id.co.bni.qris.repositori;

import id.co.bni.qris.mpm.model.user.RefreshToken;
import id.co.bni.qris.utils.RefreshableRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends RefreshableRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);
}