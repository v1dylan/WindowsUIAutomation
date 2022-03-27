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

import com.sun.jna.Function;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * @author Mark Humphreys
 * Date 05/06/2017.
 */
public class IUIAutomationTogglePatternConverter {
    private static int METHODS = 8; // 0-2 IUnknown,
                                    // 3-7 IUIAutomationTogglePattern

    private static final int TOGGLE = 3;
    private static final int CURRENT_TOGGLE_STATE = 4;

    public static IUIAutomationTogglePattern pointerToInterface(
            final PointerByReference ptr) {
        final Pointer interfacePointer = ptr.getValue();
        final Pointer vTablePointer = interfacePointer.getPointer(0);
        final Pointer[] vTable = new Pointer[METHODS];
        vTablePointer.read(0, vTable, 0, vTable.length);
        return new IUIAutomationTogglePattern() {
            // IUnknown

            @Override
            public WinNT.HRESULT QueryInterface(Guid.REFIID byValue,
                                                PointerByReference pointerByReference) {
                Function f = Function.getFunction(vTable[0],
                        Function.ALT_CONVENTION);
                return new WinNT.HRESULT(f.invokeInt(
                        new Object[]{interfacePointer,
                                byValue, pointerByReference}));
            }

            @Override
            public int AddRef() {
                Function f = Function.getFunction(vTable[1],
                        Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer});
            }

            public int Release() {
                Function f = Function.getFunction(vTable[2],
                        Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer});
            }

            public int toggle() {
                Function f = Function.getFunction(vTable[TOGGLE],
                        Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer});
            }

            public int getCurrentToggleState(IntByReference ibr) {
                Function f = Function.getFunction(vTable[CURRENT_TOGGLE_STATE],
                        Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, ibr});
            }
        };
    }
}
