/*  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package javax.portlet.tck.portlets;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import static java.util.logging.Logger.*;
import javax.xml.namespace.QName;
import javax.portlet.*;
import javax.portlet.filter.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.portlet.tck.beans.*;
import javax.portlet.tck.constants.*;
import static javax.portlet.tck.beans.JSR286ApiTestCaseDetails.*;
import static javax.portlet.tck.constants.Constants.*;
import static javax.portlet.PortletSession.*;
import static javax.portlet.ResourceURL.*;

/**
 * This portlet implements several test cases for the JSR 362 TCK. The test case names
 * are defined in the /src/main/resources/xml-resources/additionalTCs.xml
 * file. The build process will integrate the test case names defined in the 
 * additionalTCs.xml file into the complete list of test case names for execution by the driver.
 *
 * This is the main portlet for the test cases. If the test cases call for events, this portlet
 * will initiate the events, but not process them. The processing is done in the companion 
 * portlet AnnotationTests_ProcessAction_ApiAction_event
 *
 */
public class AnnotationTests_ProcessAction_ApiAction implements Portlet, ResourceServingPortlet {
   private static final String LOG_CLASS = 
         AnnotationTests_ProcessAction_ApiAction.class.getName();
   private final Logger LOGGER = Logger.getLogger(LOG_CLASS);
   
   private PortletConfig portletConfig = null;

   @Override
   public void init(PortletConfig config) throws PortletException {
      this.portletConfig = config;
   }

   @Override
   public void destroy() {
   }
   
   @Override
   public void processAction(ActionRequest portletReq, ActionResponse portletResp)
         throws PortletException, IOException {
      LOGGER.entering(LOG_CLASS, "main portlet processAction entry");
      

      portletResp.setRenderParameters(portletReq.getParameterMap());
      long tid = Thread.currentThread().getId();
      portletReq.setAttribute(THREADID_ATTR, tid);

      StringWriter writer = new StringWriter();

      JSR286ApiTestCaseDetails tcd = new JSR286ApiTestCaseDetails();

      // Create result objects for the tests

      ClassChecker cc = new ClassChecker(javax.portlet.ProcessAction.class);

      /* TestCase: V2AnnotationTests_ProcessAction_ApiAction_name             */
      /* Details: "Method name(): On an action request, the method is         */
      /* executed if the parameter \"javax.portlet.action\" matches the       */
      /* name field"                                                          */
      TestResult tr0 = tcd.getTestResultFailed(V2ANNOTATIONTESTS_PROCESSACTION_APIACTION_NAME);
      String nme=portletReq.getParameter(ActionRequest.ACTION_NAME);
 	  if(nme.equals(V2ANNOTATIONTESTS_PROCESSACTION_APIACTION_NAME)) {
 		 tr0.setTcSuccess(true);
 	  } else {
 		 tr0.setTcSuccess(false);
 	  }
 	  tr0.writeTo(writer);
 	  
      portletReq.getPortletSession().setAttribute(
                   Constants.RESULT_ATTR_PREFIX + "AnnotationTests_ProcessAction_ApiAction",
                   writer.toString(), APPLICATION_SCOPE);
   }

   @Override
   public void serveResource(ResourceRequest portletReq, ResourceResponse portletResp)
         throws PortletException, IOException {
      LOGGER.entering(LOG_CLASS, "main portlet serveResource entry");

      long tid = Thread.currentThread().getId();
      portletReq.setAttribute(THREADID_ATTR, tid);

      PrintWriter writer = portletResp.getWriter();

   }

   @Override
   public void render(RenderRequest portletReq, RenderResponse portletResp)
         throws PortletException, IOException {
      LOGGER.entering(LOG_CLASS, "main portlet render entry");

      long tid = Thread.currentThread().getId();
      portletReq.setAttribute(THREADID_ATTR, tid);

      PrintWriter writer = portletResp.getWriter();

      PortletSession ps = portletReq.getPortletSession();
      String msg = (String) ps.getAttribute(RESULT_ATTR_PREFIX + "AnnotationTests_ProcessAction_ApiAction", APPLICATION_SCOPE);
      if (msg != null) {
         writer.write("<p>" + msg + "</p><br/>\n");
         ps.removeAttribute(RESULT_ATTR_PREFIX + "AnnotationTests_ProcessAction_ApiAction", APPLICATION_SCOPE);
      }

      /* TestCase: V2AnnotationTests_ProcessAction_ApiAction_name             */
      /* Details: "Method name(): On an action request, the method is         */
      /* executed if the parameter \"javax.portlet.action\" matches the       */
      /* name field"                                                          */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameter(ActionRequest.ACTION_NAME,V2ANNOTATIONTESTS_PROCESSACTION_APIACTION_NAME);
         TestButton tb = new TestButton("V2AnnotationTests_ProcessAction_ApiAction_name", aurl);
         tb.writeTo(writer);
      }

   }

}
