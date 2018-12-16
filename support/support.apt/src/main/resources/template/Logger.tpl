/**
 * Generated file date : {{now}}
 */
package {{package.getQualifiedName}};

import java.lang.Override;
import javax.annotation.Generated;

import in.java.support.util.LambdaLogger;
import in.java.support.util.LambdaLoggerFactory;

@Generated(value="{{generatedProcessor}}", date="{{now}}", comments = "{{package.getQualifiedName}}.{{& className}}")
privileged aspect {{& aspectClassName}} {

    private final static LambdaLogger {{& className}}.log = LambdaLoggerFactory.getLogger("ROOT");

}
