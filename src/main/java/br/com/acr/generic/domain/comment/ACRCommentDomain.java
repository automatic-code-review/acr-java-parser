package br.com.acr.generic.domain.comment;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Getter
@Setter
public class ACRCommentDomain {

    private String id;

    private String comment;

    private ACRPositionDomain position;

    public String getId() throws NoSuchAlgorithmException {

        if (id == null) {

            id = new BigInteger(1, MessageDigest.getInstance("MD5").digest(comment.getBytes())).toString(16);

        }

        return id;

    }

}
