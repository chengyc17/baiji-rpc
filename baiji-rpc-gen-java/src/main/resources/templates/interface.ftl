<#setting number_format="0">
package ${packageName};

public interface ${serviceName} {

    <#list serviceMethods as method>
        /**
        * ${method.methodName} method.
        * @param request the ${method.reqType} request object.
        * @return the ${method.resType} response object.
        */
        ${method.resType} ${method.methodName}(${method.reqType} request) throws Exception;
    </#list>
}
