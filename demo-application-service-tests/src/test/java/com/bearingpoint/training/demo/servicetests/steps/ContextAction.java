package com.bearingpoint.training.demo.servicetests.steps;

import com.bearingpoint.training.demo.servicetests.context.TestContext;

public class ContextAction {
    public void setUserInContext(String user) {
        TestContext.setUser(user);
    }
}
