/*
 * Copyright 2015-2024. Ritense BV, the Netherlands.
 *
 *  Licensed under EUPL, Version 1.2 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" basis,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ritense.valtimoplugins.alfresco

import com.ritense.valtimo.contract.utils.SecurityUtils
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.nio.charset.Charset
import java.util.Date
import io.github.oshai.kotlinlogging.KotlinLogging

private const val FALLBACK_USER = "System"
private val logger = KotlinLogging.logger {}

class AlfrescoTokenGeneratorService {

    fun generateToken(secretKey: String, clientId: String): String {
        logger.debug { "generating a token" }

        if (secretKey.length < 32) {
            throw IllegalStateException("SecretKey needs to be at least 32 in length")
        }
        val signingKey = Keys.hmacShaKeyFor(secretKey.toByteArray(Charset.forName("UTF-8")))

        val jwtBuilder = Jwts.builder()
        jwtBuilder
            .setIssuer(clientId)
            .setIssuedAt(Date())
            .claim("client_id", clientId)

        appendUserInfo(jwtBuilder)
        return jwtBuilder
            .signWith(signingKey, SignatureAlgorithm.HS256)
            .compact()
    }

    private fun appendUserInfo(jwtBuilder: JwtBuilder) {
        val authenticated = SecurityUtils.isAuthenticated()
        val userLogin = if (authenticated) SecurityUtils.getCurrentUserLogin() else FALLBACK_USER
        val userId = userLogin ?: FALLBACK_USER

        jwtBuilder
            .claim("user_id", userId)
            .claim("user_representation", "")

        if (authenticated) {
            val roles = SecurityUtils.getCurrentUserRoles()
            if (!roles.isNullOrEmpty()) {
                jwtBuilder.claim("roles", roles)
            }
        }
    }
}
