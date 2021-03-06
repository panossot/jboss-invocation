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

import java.io.Serializable;

/**
 * @author <a href="mailto:david.lloyd@redhat.com">David M. Lloyd</a>
 */
class TerminalInterceptor implements Interceptor, Serializable {

    static final Interceptor INSTANCE = new TerminalInterceptor();
    static final InterceptorFactory FACTORY = new ImmediateInterceptorFactory(INSTANCE);

    private static final long serialVersionUID = -2792151547173027051L;

    public Object processInvocation(final InterceptorContext context) throws Exception {
        return null;
    }

    protected Object readResolve() {
        return INSTANCE;
    }

    public String toString() {
        return "terminal interceptor";
    }
}
