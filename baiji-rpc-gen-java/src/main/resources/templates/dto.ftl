<#setting number_format="0">
package ${packageName};

import java.util.Map;
import java.util.List;
import java.util.Objects;

/**
* ${className} 实体类
*/
public class ${className} {
    <#list fields as field>
        private ${field.dataType} ${field.fieldName};
    </#list>

    <#list fields as field>
        /**
        * 获取 ${field.fieldName}
        * @return ${field.fieldName}
        */
        public ${field.dataType} get${field.fieldName?cap_first}() {
        return ${field.fieldName};
        }

        /**
        * 设置 ${field.fieldName}
        * @param ${field.fieldName} ${field.fieldName}
        */
        public void set${field.fieldName?cap_first}(${field.dataType} ${field.fieldName}) {
        this.${field.fieldName} = ${field.fieldName};
        }
    </#list>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ${className} that = (${className}) o;
        return
        <#list fields as field>
            Objects.equals(${field.fieldName}, that.${field.fieldName})<#if field_has_next>
            &&<#else>;
        </#if>
        </#list>
    }

    @Override
    public int hashCode() {
        <#if fields?size == 1>
            return Objects.hash(${fields[0].fieldName});
        <#else>
            return Objects.hash(<#list fields as field>${field.fieldName}<#if field_has_next>, </#if></#list>);
        </#if>
    }

    @Override
    public String toString() {
        return "${className}{" +
        <#list fields as field>
            "${field.fieldName}=" + ${field.fieldName} +<#if field_has_next>
            ", " +<#else>
            '}'
        </#if>
        </#list>
        ;
    }
}
