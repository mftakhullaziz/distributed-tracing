package com.example.distrimo.aspect;

import io.micrometer.tracing.Tracer;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepositoryTraceAspect {

    private final Tracer tracer;

    public RepositoryTraceAspect(Tracer tracer) {
        this.tracer = tracer;
    }

    @Pointcut("execution(* org.springframework.data.repository.Repository+.*(..))")
    public void repositoryMethods() {}

    @Before("repositoryMethods()")
    public void beforeRepositoryCall() {
        // Start the span for repository method call
        tracer.currentSpan().tag("repository.call", "start");
    }

    @After("repositoryMethods()")
    public void afterRepositoryCall() {
        // Close the span after the repository method call
        tracer.currentSpan().tag("repository.call", "end");
    }
}
