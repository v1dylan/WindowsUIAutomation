/*
 * Copyright 2016-17 inpwtepydjuf@gmail.com
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
package mmarquee.automation.controls;

import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;

/**
 * Specialist pane that represents the NUIPane (part of the MS ribbon controls).
 *
 * @author Mark Humphreys
 * Date 02/03/2016.
 */
public final class NUIPane extends Panel {

    /**
     * The class name of this pane type.
     */
	public final static String CLASS_NAME = "NUIPane";
	
    /**
     * Construct the NUIPane.
     *
     * @param builder The builder
     * @throws AutomationException Automation error.
     */
    public NUIPane(final ElementBuilder builder)
            throws AutomationException {
        super(builder);
        assertClassName(CLASS_NAME);
    }

    /**
     * Get the NetUIHWND associated with the given index.
     *
     * @param index The index.
     * @return The NetUIHWND.
     * @throws AutomationException Automation error.
     */
    public NetUIHWND getNetUIHWND(final int index)
            throws AutomationException {
        return new NetUIHWND(new ElementBuilder(this.getElementByControlType(index,
                ControlType.Pane, NetUIHWND.CLASS_NAME)));
    }

    /**
     * Get the NetUIHWND. with search criteria.
     *
     * @param search The search criteria
     * @return The NetUIHWND.
     * @throws AutomationException Automation error.
     */
    public NetUIHWND getNetUIHWND(final Search search)
            throws AutomationException {
        return getNetUIHWND(search.getIndex());
    }
}