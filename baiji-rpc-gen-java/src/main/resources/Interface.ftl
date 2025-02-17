package ${packageName};

/**
* ${className} 实体类
*/
public class ${serviceName} {

    <#list serviceMethods as method>
        /**
        * ${method.name} method.
        * @param request the ${method.requestType} request object.
        * @return the ${method.responseType} response object.
        */
        ${method.responseType} ${method.name}(${method.requestType} request) throw Exception;
    </#list>
}
