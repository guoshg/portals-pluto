/*
 * Copyright 2003,2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/* 

 */

package org.apache.pluto.core;

import org.apache.pluto.PortletContainer;

import javax.portlet.PortletRequest;
import javax.portlet.PortletContext;
import javax.servlet.http.HttpServletRequest;

public interface InternalPortletRequest extends PortletRequest {

    void setPortletContext(PortletContext context);

    InternalPortletWindow getInternalPortletWindow();

    PortletContainer getPortletContainer();

    void setIncluded(boolean included);

    boolean isIncluded();

    HttpServletRequest getHttpServletRequest();
}

