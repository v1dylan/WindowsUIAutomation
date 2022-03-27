package com.automationanywhere.botcommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;
import com.automationanywhere.commandsdk.annotations.Execute;
import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;
//BotCommand makes a class eligible for being considered as an action.
@BotCommand

//CommandPks adds required information to be dispalable on GUI.
@CommandPkg(
        //Unique name inside a package and label to display.
        name = "concatenate", label = "[[Concatenate.label]]",
        node_label = "[[Concatenate.node_label]]", description = "[[Concatenate.description]]", icon = "pkg.svg",

        //Return type information. return_type ensures only the right kind of variable is provided on the UI.
        return_label = "[[Concatenate.return_label]]", return_type = STRING, return_required = true)

public class GetTableCommand {
    //Messages read from full qualified property file name and provide i18n capability.
    private static final Messages MESSAGES = MessagesFactory
            .getMessages("com.automationanywhere.botcommand.samples.messages");

    //Identify the entry point for the action. Returns a Value<String> because the return type is String.
    @Execute
    public Value<String> action(

            @Idx(index = "1", type = TEXT)
            @Pkg(label = "[[Concatenate.firstString.label]]")
            @NotEmpty
                    String ApplicationPath,

            @Idx(index = "2", type = TEXT)
            @Pkg(label = "[[Concatenate.secondString.label]]")
            @NotEmpty
                    String WindowTitle,

            @Idx(index = "3", type = TEXT)
            @Pkg(label = "[[Concatenate.secondString.label]]")
            @NotEmpty
                    String ParentElement,

            @Idx(index = "4", type = TEXT)
            @Pkg(label = "[[Concatenate.secondString.label]]")
            @NotEmpty
                    String Table) {







        String result = null;

        //Return StringValue.
        return new StringValue(result);
    }

}
