package sif.testcenter.custom_rules;

import sif.testcenter.Policy;
import sif.testcenter.PolicyType;
import sif.utility.Translator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("unused")
@XmlRootElement(name = "RulePolicy")
public class RulesPolicy  extends Policy{
    private List<Rule> rules = new ArrayList<>();

    public RulesPolicy() {
        super();
        setName(Translator.tl("RulesPolicy.Name"));
        setDescription(Translator.tl("RulesPolicy.Description"));
        setBackground(Translator.tl("RulesPolicy.Background"));
        setSolution(Translator.tl("RulesPolicy.Solution"));
        setPolicyType(PolicyType.DYNAMIC);
    }

    @XmlElementWrapper(name = "rules")
    @XmlElements({
            @XmlElement(name = "rule", type = Rule.class),
    })

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }


}