/*
 * Copyright 2013-2020 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Positive;

public class WavefrontConfiguration {

    @JsonProperty
    private String uri;

    @JsonProperty
    private String apiToken = "apitest";

    @JsonProperty
    @Positive
    private int batchSize = 10_000;

    public String getUri() {
        return uri;
    }

    public String getapiToken() {
        return apiToken;
    }

    public int getBatchSize() {
        return batchSize;
    }
}
