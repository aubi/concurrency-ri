/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) [2022] Payara Foundation and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://github.com/payara/Payara/blob/master/LICENSE.txt
 * See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * The Payara Foundation designates this particular file as subject to the "Classpath"
 * exception as provided by the Payara Foundation in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package org.glassfish.enterprise.concurrent;

import jakarta.annotation.Priority;
import jakarta.enterprise.concurrent.Asynchronous;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

/**
 * Handler for @Asynchronous.
 *
 * @author Petr Aubrecht <aubrecht@asoftware.cz>
 */
@Interceptor
@Asynchronous
@Priority(Interceptor.Priority.PLATFORM_AFTER)
public class AsynchronousHandler {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        System.out.println("YES, here!");
        return context.proceed();
    }

//    <T> void processAsynchronousAnnotatedMethod(@Observes @WithAnnotations(Asynchronous.class) ProcessAnnotatedType<T> processAnnotatedType) throws Exception {
//        AnnotatedType<T> type = processAnnotatedType.getAnnotatedType();
//        boolean isAsynchronousMethod = type.isAnnotationPresent(Asynchronous.class);
//        Class<?> targetClass = type.getJavaClass();
//        for (AnnotatedMethodConfigurator<?> methodConfigurator : processAnnotatedType.configureAnnotatedType().methods()) {
//            AnnotatedMethod<?> method = methodConfigurator.getAnnotated();
//            if (isAsynchronousMethod) {
//                execute(targetClass, method.getJavaMember());
//            }
//        }
//
//    }
//
//    private void execute(Class<?> targetClass, Method javaMember) {
//        AsyncFuture asyncResult = new AsyncFuture() {
//
//            @Override
//            public boolean cancel(boolean mayInterruptIfRunning) {
//                boolean res = super.cancel(mayInterruptIfRunning);
//                if (mayInterruptIfRunning) {
//                    logger.log(Level.FINE, "Asynchronous computation was cancelled by caller.");
//                    if (mayInterruptIfRunning) {
//                        workers.forEach(worker -> worker.interrupt());
//                    }
//                }
//                return res;
//            }
//        };
//
//        Runnable completionTask = () -> {
//            if (!asyncResult.isCancelled() && !Thread.currentThread().isInterrupted()) {
//                boolean returned = false;
//                try {
//                    trace("runAsynchronous");
//                    if (shared.requestContext != null) {
//                        shared.requestContext.activate();
//                    }
//                    Object res = task.call();
//                    returned = true;
//                    Future<?> futureResult = AsynchronousPolicy.toFuture(res);
//                    if (!asyncResult.isCancelled()) { // could be cancelled in the meanwhile
//                        if (!asyncResult.isDone()) {
//                            asyncResult.complete(futureResult.get());
//                        }
//                    } else {
//                        futureResult.cancel(true);
//                    }
//                } catch (Exception | Error ex) {
//                    // Note that even ExecutionException unpacked to the exception originally used to complete the future
//                    asyncResult.setExceptionThrown(!returned);
//                    asyncResult.completeExceptionally(returned && ex instanceof ExecutionException ? ex.getCause() : ex);
//                } finally {
//                    if (shared.requestContext != null) {
//                        shared.requestContext.deactivate();
//                    }
//                    endTrace();
//                }
//            }
//        };
//
//        asyncExecution.submit(completionTask);
//    }

}
