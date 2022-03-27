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

import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.*;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.uiautomation.IUIAutomationExpandCollapsePattern;
import mmarquee.uiautomation.IUIAutomationExpandCollapsePatternConverter;
import mmarquee.uiautomation.TreeScope;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Wrapper for the MainMenu element.
 * @author Mark Humphreys
 * Date 09/02/2016.
 */
public class MainMenu extends Menu {

    /**
     * The parent of the element.
     */
    private Element parent;

    /**
     * Gets the parent element of the menu element.
     * @return The parent element
     */
    public Element getParentElement() {
        return this.parent;
    }

    /**
     * The control type.
     */
    public static ControlType controlType = ControlType.MenuBar;

    /**
     * Constructor for MainMenu.
     *
     * @param builder The builder
     */
    public MainMenu(final ElementBuilder builder) {
        super(builder);
        this.parent = builder.getParent();
    }

    /**
     * Gets the raw pattern from the given element.
     * @param item The Element
     * @return The raw collapse pattern
     * @throws AutomationException Failed to get pattern
     */
    public IUIAutomationExpandCollapsePattern getExpandCollapsePatternFromItem(
            Element item)
                        throws AutomationException {
        PointerByReference pElement =
                item.getPattern(PatternID.ExpandCollapse.getValue());

        Unknown unkConditionA = makeUnknown(pElement.getValue());

        PointerByReference pUnknownA = new PointerByReference();

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(
                new Guid.REFIID(IUIAutomationExpandCollapsePattern.IID),
                pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {
            return IUIAutomationExpandCollapsePatternConverter.pointerToInterface(pUnknownA);
        } else {
            throw new AutomationException("QueryInterface failed");
        }
    }

    /**
     * Get the menu item associated with the name.
     * @param name First Name
     * @return The menu item that matches the name
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    @Override
    public MenuItem getMenuItem(final String name)
            throws PatternNotFoundException, AutomationException {
    	return getMenuItem(name, "");
    }

    /**
     * Sleep period to ensure menu is shown.
     */
    private final static int WAIT_TIME = 750;

    /**
     * Get the menu item associated with the hierarchy of names.
     * @param name0 First Name
     * @param name1 Second name
     * @return The menu item that matches the name
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public MenuItem getMenuItem(final String name0,
                                final String name1)
            throws PatternNotFoundException, AutomationException {


        Element item = this.findFirst(
                new TreeScope(TreeScope.CHILDREN),
                    this.createAndCondition(
                        this.createNamePropertyCondition(name0),
                        this.createControlTypeCondition(ControlType.MenuItem)));

        if (item == null) {
            throw new ItemNotFoundException("Failed to find element: " + name0);
        }
        
        MenuItem menuItem =
                new MenuItem(new ElementBuilder(item));
        menuItem.parentMenuName = item.getName();
        menuItem.mainMenuParentElement = this.getParentElement();

        if (name1 == null || name1.isEmpty()) {
        	return menuItem;
        }
        

        menuItem.expand();
        
        try {
            Thread.sleep(WAIT_TIME);
        } catch (Exception ex) {
            // Seems to be fine
        }
        
        return menuItem.getMenuItem(name1);
    }

    /**
     * Get the menu item matching with the hierarchy of names.
     * @param name0Pattern First Name matching pattern
     * @param name1Pattern Second name matching pattern
     * @return The menu item that matches the name
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public MenuItem getMenuItem(final Pattern name0Pattern,
                                final Pattern name1Pattern)
            throws PatternNotFoundException, AutomationException {
    	List<Element> collection;

        Element item = null;

        collection = this.findAll(new TreeScope(TreeScope.CHILDREN),
        		this.createControlTypeCondition(ControlType.MenuItem));

        for (Element element : collection) {
            String name = element.getName();

            if (name != null && name0Pattern.matcher(name).matches()) {
                item = element;
                break;
            }
        }
        
        if (item == null) {
            throw new ItemNotFoundException("Failed to find element matching " + name0Pattern);
        }
        
        MenuItem menuItem =
                new MenuItem(new ElementBuilder(item));
        menuItem.parentMenuName = item.getName();
        menuItem.mainMenuParentElement = this.getParentElement();

        if (name1Pattern == null) {
        	return menuItem;
        }
        

        menuItem.expand();
        
        try {
            Thread.sleep(WAIT_TIME);
        } catch (Exception ex) {
            // Seems to be fine
        }
        
        return menuItem.getMenuItem(name1Pattern);
    }
    
    /**
     * Get the menu item associated with the automationID.
     * @param automationId The automation ID to look for
     * @return The menu item that matches the name
     * @throws AutomationException Something has gone wrong
     */
    public MenuItem getMenuItemByAutomationId(
            final String automationId)
            throws AutomationException {
    	
        Element item = this.findFirst(
                new TreeScope(TreeScope.DESCENDANTS),
                    this.createAndCondition(
                        this.createAutomationIdPropertyCondition(automationId),
                        this.createControlTypeCondition(ControlType.MenuItem)));

        return new MenuItem(new ElementBuilder(item));
    }
    
    /**
     * Gets the items associated with this menu control.
     * @return The list of items
     * @throws AutomationException Something has gone wrong
     */
    public List<MenuItem> getItems() throws AutomationException {
        List<Element> items =
                this.findAll(new TreeScope(TreeScope.DESCENDANTS),
                        this.createControlTypeCondition(ControlType.MenuItem));

        List<MenuItem> list = new ArrayList<>();
        
        for (Element item: items) {
            list.add(new MenuItem(new ElementBuilder(item)));
        }

        return list;
    }
}
