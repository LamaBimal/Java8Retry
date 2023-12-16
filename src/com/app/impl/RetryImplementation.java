package com.app.impl;

import java.io.IOException;

// creating functional interface for the retryImplementation
@FunctionalInterface
public interface RetryImplementation {
    Boolean run() throws IOException;
}
