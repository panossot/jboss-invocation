/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2016 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
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

package org.jboss.invocation;

import org.wildfly.common.Assert;

/**
 * An interceptor which executes the synchronous part of an invocation.
 *
 * @author <a href="mailto:david.lloyd@redhat.com">David M. Lloyd</a>
 */
public final class SynchronousInterceptor implements AsynchronousInterceptor {
    private final Interceptor[] interceptors;

    /**
     * Construct a new instance.
     *
     * @param interceptors the interceptors to apply to the synchronous invocation (must not be {@code null})
     */
    public SynchronousInterceptor(final Interceptor[] interceptors) {
        Assert.checkNotNullParam("interceptors", interceptors);
        this.interceptors = interceptors;
    }

    public CancellationHandle processInvocation(final AsynchronousInterceptorContext context, final ResultHandler resultHandler) {
        InterceptorContext synchContext = context.toSynchronous();
        synchContext.setInterceptors(interceptors);
        try {
            resultHandler.setResult(ResultSupplier.succeeded(synchContext.proceed()));
        } catch (Exception e) {
            resultHandler.setException(e);
        }
        return CancellationHandle.NULL;
    }
}
