package sif.testcenter.custom_rules;

import com.google.inject.Inject;
import sif.model.Cell;
import sif.model.values.ValueHelper;
import sif.model.values.ValueType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * The Regex Checker for the custom_rules Facility
 */
public class CustomChecker {
    @Inject
    private ValueHelper valueHelper;

    public boolean isFulfilled(RuleCondition ruleCondition, Cell cell) {

        // convert to Regex, or check regex pattern
        switch (ruleCondition.getConditionType()) {
            case Regex:
                if (checkRegex(ruleCondition.getConditionValue(), cell.getValue().getValueString())) {
                    return true;
                }
                return false;
            case CharacterCount:
                try {
                    if (cell.getValue().getValueString().length() <= Integer.parseInt(ruleCondition.getConditionValue())+1) {
                        return true;
                    }
                } catch (Exception e) {

                }

                return false;
            case Empty:
                if (cell.getValue().getType() == ValueType.BLANK) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    /**
     * Adds Checks to the pattern and matches it with the input while being greedy and lazy
     * @param pattern
     * @param input
     * @return
     */
    public boolean checkRegex (String pattern, String input) {
        Pattern p = Pattern.compile("(^|\\W)" + pattern + "($|\\W)");
        Matcher m = p.matcher(input);
        if (m.find()) {
            return true;
        }
        else {
            return false;
        }


    }



}
