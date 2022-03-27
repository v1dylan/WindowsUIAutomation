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

/**
 * Wrapper for the Calendar element.
 *
 * @author Mark Humphreys
 * Date 16/02/2016.
 *
 * Implements IGridProvider, IScrollProvider, ITableProvider, IValueProvider
 */
public class Calendar extends AutomationBase
        implements ImplementsValue {

    /**
     * Constructor for the Calendar.
     *
     * @param builder The element builder
     */
    public Calendar(final ElementBuilder builder) {
        super(builder);
    }
}