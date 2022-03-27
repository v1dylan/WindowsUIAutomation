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
package mmarquee.automation.pattern;

import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationException;
import mmarquee.automation.Element;
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;
import mmarquee.uiautomation.*;

import java.util.List;

/**
 * Wrapper for the Selection pattern.
 *
 * @author Mark Humphreys
 * Date 25/02/2016.
 */
public class Selection extends BasePattern {

    /**
     * Constructor for the value pattern.
     *
     * @param element The automation element for which the pattern is valid
     * @throws AutomationException If something goes wrong
     */
    public Selection(final Element element) throws AutomationException {
    	super(element);
        this.IID = IUIAutomationSelectionPattern.IID;
        this.patternID = PatternID.Selection;
        this.availabilityPropertyID = PropertyID.IsSelectionPatternAvailable;
    }

    /**
     * The raw IUIAutomationSelectionPattern pattern.
     */
    IUIAutomationSelectionPattern rawPattern;

    /**
     * The raw IUIAutomationSelectionPattern2 pattern.
     */
    IUIAutomationSelectionPattern2 rawPattern2;

    /**
     * Gets the pointer.
     *
     * @return Underlying pointer
     * @throws AutomationException Automation has gone wrong
     */
    private IUIAutomationSelectionPattern getPattern()
            throws AutomationException {
    	return getPattern(rawPattern, this::convertPointerToInterface);
    }

    /**
     * Gets the pointer to the second selection pattern.
     *
     * @return Underlying pointer to the selection2 pattern
     * @throws AutomationException Automation has gone wrong
     */
    private IUIAutomationSelectionPattern2 getPattern2()
            throws AutomationException {
        return getPattern(rawPattern2, this::convertPointerToInterface2);
    }

    /**
     * Gets the current selection.
     *
     * @return The current selection
     * @throws AutomationException Something has gone wrong
     */
    public List<Element> getCurrentSelection() throws AutomationException {

        PointerByReference pbr = new PointerByReference();

        final int res = this.getPattern().getCurrentSelection(pbr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return collectionToList(getAutomationElementArrayFromReference(pbr));
    }

    /**
     * Gets the current selected item.
     *
     * @return The current selected item
     * @throws AutomationException Something has gone wrong
     */
    public Element getCurrentSelectedItem() throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.getPattern2().getCurrentCurrentSelectedItem(pbr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return new Element(getAutomationElementFromReference(pbr));
    }

    /**
     * Convert the unknown pointer to selection pattern.
     *
     * @param pUnknown The unknown pointer
     * @return IUIAutomationSelectionPattern the converted pointer
     */
    IUIAutomationSelectionPattern convertPointerToInterface(PointerByReference pUnknown) {
        return IUIAutomationSelectionPatternConverter.pointerToInterface(pUnknown);
    }

    /**
     * Convert the unknown pointer to selection2 pattern.
     *
     * @param pUnknown The unknown pointer
     * @return IUIAutomationSelectionPattern2 the converted pointer
     */
    IUIAutomationSelectionPattern2 convertPointerToInterface2(PointerByReference pUnknown) {
        return IUIAutomationSelectionPattern2Convertor.pointerToInterface(pUnknown);
    }

    /**
     * Convert the unknown pointer to an array.
     *
     * @param pUnknown The unknown pointer
     * @return IUIAutomationSelectionPattern the converted pointer
     */
    public IUIAutomationElementArray convertPointerToElementArray(PointerByReference pUnknown) {
        return IUIAutomationElementArrayConverter.pointerToInterface(pUnknown);
    }

    /**
     * Gets the current selection.
     *
     * @return List of selected items
     * @throws AutomationException Something has gone wrong
     */
    public List<Element> getSelection() throws AutomationException {
        return getCurrentSelection();
    }

    /**
     * Returns whether the selection supports multiple selection.
     *
     * @return Value from automation
     * @throws AutomationException Something has gone wrong
     */
    public boolean canSelectMultiple() throws AutomationException {
        IntByReference ibr = new IntByReference();

        final int res = this.getPattern().getCurrentCanSelectMultiple(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return (ibr.getValue() == 1);
    }
}
