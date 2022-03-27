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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mark Humphreys
 * Date 13/07/2016.
 */
public enum ToggleState {
    /**
     * Toggled off.
     */
    OFF(0),

    /**
     * Toggled on.
     */
    ON(1),

    /**
     * INDETERMINATE toggle.
     */
    INDETERMINATE(2);

    /**
     * The underlying value.
     */
    private int value;

    /**
     * Gets the value associated with this enumeration.
     * @return The associated value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Constructor for ToggleState.
     * @param inValue The value of the object.
     */
    ToggleState(final int inValue) {
        this.value = inValue;
    }

    /**
     * Map of integer to value.
     */
    private static final Map<Integer, ToggleState> INT_TO_TYPE_MAP =
            new HashMap<>();

    static {
        for (ToggleState type : ToggleState.values()) {
            INT_TO_TYPE_MAP.put(type.value, type);
        }
    }

    /**
     * Gets the enumeration from the given integer.
     * @param i The given integer.
     * @return The value (as an ToggleState).
     */
    public static ToggleState fromInt(final int i) {
        ToggleState type = INT_TO_TYPE_MAP.get(i);
        if (type == null) {
            return ToggleState.OFF;
        }

        return type;
    }
}
