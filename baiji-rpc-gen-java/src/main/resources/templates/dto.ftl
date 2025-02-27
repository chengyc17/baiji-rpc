package ${packageName};

import java.util.Map;
import java.util.List;

/**
* ${className} 实体类
*/
public class ${className} {
    <#list fields as field>
        private ${field.type} ${field.name};
    </#list>

    <#list fields as field>
        /**
        * 获取 ${field.name}
        * @return ${field.name}
        */
        public ${field.type} get${field.name?cap_first}() {
        return ${field.name};
        }

        /**
        * 设置 ${field.name}
        * @param ${field.name} ${field.name}
        */
        public void set${field.name?cap_first}(${field.type} ${field.name}) {
        this.${field.name} = ${field.name};
        }
    </#list>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ${className} that = (${className}) o;
        <#list fields as field>
            return Objects.equals(${field.name}, that.${field.name})<#if field_has_next>
            &&<#else>;
        </#if>
        </#list>
    }

    @Override
    public int hashCode() {
        <#if fields?size == 1>
            return Objects.hash(${fields[0].name});
        <#else>
            return Objects.hash(<#list fields as field>${field.name}<#if field_has_next>, </#if></#list>);
        </#if>
    }

    @Override
    public String toString() {
        return "${className}{" +
        <#list fields as field>
            "${field.name}=" + ${field.name} +<#if field_has_next>
            ", " +<#else>
            '}';
        </#if>
        </#list>
    }
}
