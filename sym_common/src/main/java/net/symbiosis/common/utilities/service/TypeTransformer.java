package net.symbiosis.common.utilities.service;


@FunctionalInterface
public interface TypeTransformer<T, K> {

    K transform(T instance);

}
