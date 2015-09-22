/*
 * Copyright 2015 Comcast Cable Communications Management, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.comcast.cdn.traffic_control.traffic_router.core.http;

import com.comcast.cdn.traffic_control.traffic_router.core.router.StatTracker.Track.ResultType;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.Date;

// Using Josh Bloch Builder pattern so suppress these warnings.
@SuppressWarnings({"PMD.MissingStaticMethodInNonInstantiatableClass",
        "PMD.AccessorClassGeneration",
        "PMD.NPathComplexity",
        "PMD.IfStmtsMustUseBraces",
        "PMD.CyclomaticComplexity"})
public class HTTPAccessRecord {
    private final Date requestDate;
    private final HttpServletRequest request;
    private final URL responseURL;
    private final int responseCode;
    private final ResultType resultType;
    private final String rerr;

    public Date getRequestDate() {
        return requestDate;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public URL getResponseURL() {
        return responseURL;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public String getRerr() {
        return rerr;
    }

    public static class Builder {
        private final Date requestDate;
        private final HttpServletRequest request;
        private int responseCode = -1;
        private URL responseURL;
        private ResultType resultType;
        private String rerr;

        public Builder(final Date requestDate, final HttpServletRequest request) {
            this.requestDate = requestDate;
            this.request = request;
        }

        public Builder(final HTTPAccessRecord httpAccessRecord) {
            this.requestDate = httpAccessRecord.requestDate;
            this.request = httpAccessRecord.request;
            this.responseURL = httpAccessRecord.responseURL;
            this.responseCode = httpAccessRecord.responseCode;
        }

        public Builder responseCode(final int responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        public Builder responseURL(final URL responseURL) {
            this.responseURL = responseURL;
            return this;
        }

        public Builder resultType(final ResultType resultType) {
            this.resultType = resultType;
            return this;
        }

        public Builder rerr(final String rerr) {
            this.rerr = rerr;
            return this;
        }

        public HTTPAccessRecord build() {
            return new HTTPAccessRecord(this);
        }
    }

    private HTTPAccessRecord(final Builder builder) {
        requestDate = builder.requestDate;
        request = builder.request;
        responseCode = builder.responseCode;
        responseURL = builder.responseURL;
        resultType = builder.resultType;
        rerr = builder.rerr;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final HTTPAccessRecord that = (HTTPAccessRecord) o;

        if (responseCode != that.responseCode) return false;
        if (requestDate != null ? !requestDate.equals(that.requestDate) : that.requestDate != null) return false;
        if (request != null ? !request.equals(that.request) : that.request != null) return false;
        if (responseURL != null ? !responseURL.equals(that.responseURL) : that.responseURL != null) return false;
        if (resultType != that.resultType) return false;
        return !(rerr != null ? !rerr.equals(that.rerr) : that.rerr != null);

    }

    @Override
    public int hashCode() {
        int result = requestDate != null ? requestDate.hashCode() : 0;
        result = 31 * result + (request != null ? request.hashCode() : 0);
        result = 31 * result + (responseURL != null ? responseURL.hashCode() : 0);
        result = 31 * result + responseCode;
        result = 31 * result + (resultType != null ? resultType.hashCode() : 0);
        result = 31 * result + (rerr != null ? rerr.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HTTPAccessRecord{" +
                "requestDate=" + requestDate +
                ", request=" + request +
                ", responseURL=" + responseURL +
                ", responseCode=" + responseCode +
                ", resultType=" + resultType +
                ", rerr='" + rerr + '\'' +
                '}';
    }
}