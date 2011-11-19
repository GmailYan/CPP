/*
 * Copyright 2007 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.sample.feedreader.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.libideas.resources.client.DataResource;
import com.google.gwt.libideas.resources.client.ImmutableResourceBundle;
import com.google.gwt.libideas.resources.client.TextResource;

/**
 * A ResourceBundle that holds the references to the resources used by
 * GwtFeedReader.
 */
public interface Resources extends ImmutableResourceBundle {
  public static final Resources INSTANCE =
      (Resources) GWT.create(Resources.class);

  /**
   * The non-structural CSS rules.
   * @gwt.resource appearance.css
   */
  public TextResource appearanceCss();

  /**
   * @gwt.resource iPhoneBackButton.png
   */
  public DataResource backButton();

  /**
   * @gwt.resource background.png
   */
  public DataResource background();

  /**
   * @gwt.resource iPhoneButton.png
   */
  public DataResource button();
  
  /**
   * @gwt.resource defaultFeeds.txt
   */
  public TextResource defaultFeeds();
  
  /**
   * @gwt.resource iPhoneArrow.png
   */
  public DataResource enter();

  /**
   * Structural CSS rules.
   * @gwt.resource layout.css
   */
  public TextResource layoutCss();

  /**
   * @gwt.resource gwtLogo.png
   */
  public DataResource logo();

  /**
   * @gwt.resource spinner.gif
   */
  public DataResource spinner();

  /**
   * @gwt.resource iPhoneToolbar.png
   */
  public DataResource toolbar();
}
