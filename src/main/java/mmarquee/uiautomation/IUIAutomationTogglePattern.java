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
package mmarquee.uiautomation;

import com.sun.jna.platform.win32.COM.IUnknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.ptr.IntByReference;

/**
 * @author Mark Humphreys
 * Date 13/07/2016.
 */
public interface IUIAutomationTogglePattern extends IUnknown {
    /**
     * The interface IID for QueryInterface et al
     */
    Guid.IID IID = new Guid.IID("{94CF8058-9B8D-4AB9-8BFD-4CD0A33C8C70}");

    /**
     * Toggles the element via the pattern.
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int toggle();

    /**
     * Gets the toggle state of the element, via the pattern.
     * @param ibr Toggle state
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int getCurrentToggleState(IntByReference ibr);
}

