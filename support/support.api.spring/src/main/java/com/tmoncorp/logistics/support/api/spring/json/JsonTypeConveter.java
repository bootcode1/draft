package in.java.support.api.spring.json;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import in.java.support.util.function.Func;
import in.java.support.util.function.Func;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

public interface JsonTypeConveter
{
	@FunctionalInterface
	public interface One<T, R> extends Function<T, R>
	{
		R apply(T t);
		
	}
	
	@FunctionalInterface
	public interface Two<T, R> extends BiFunction<T, Object, R>
	{
		R apply(T t, Object returnValue);
	}
	
	@FunctionalInterface
	public interface Three<T, R>  extends Func.Three<T, Object, ModelAndViewContainer, R>
	{
		R apply(T t, Object returnValue, ModelAndViewContainer modelView);
		
		default <V> Three<V, R> compose(Function<? super V, ? extends T> before) {
	        Objects.requireNonNull(before);
	        return (V v, Object returnValue, ModelAndViewContainer modelView) -> apply(before.apply(v), returnValue, modelView);
	    }

	    default <V> Three<T, V> andThen(Function<? super R, ? extends V> after) {
	        Objects.requireNonNull(after);
	        return (T t, Object returnValue, ModelAndViewContainer modelView)  -> after.apply(apply(t, returnValue, modelView));
	    }
	}
	
	@FunctionalInterface
	public interface Four<T, R> extends Func.Four<T, Object, ModelAndViewContainer, NativeWebRequest, R>
	{
		R apply(T t, Object returnValue, ModelAndViewContainer modelView, NativeWebRequest request);
		
		default <V> Four<V, R> compose(Function<? super V, ? extends T> before) {
	        Objects.requireNonNull(before);
	        return (V v, Object returnValue, ModelAndViewContainer modelView, NativeWebRequest request) -> apply(before.apply(v), returnValue, modelView, request);
	    }

	    default <V> Four<T, V> andThen(Function<? super R, ? extends V> after) {
	        Objects.requireNonNull(after);
	        return (T t, Object returnValue, ModelAndViewContainer modelView, NativeWebRequest request)  -> after.apply(apply(t, returnValue, modelView, request));
	    }
	}
}
