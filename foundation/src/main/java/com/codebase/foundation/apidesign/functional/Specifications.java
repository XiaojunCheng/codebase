package com.codebase.foundation.apidesign.functional;

/**
 * @author Xiaojun.Cheng
 * @date 2017/9/13
 */
public class Specifications {

    public static <T> Specification<T> TRUE() {
        return instance -> true;
    }

    public static <T> Specification<T> not(final Specification<T> specification) {
        return instance -> !specification.satisfiedBy(instance);
    }

    public static <T> AndSpecification<T> and(final Specification<T>... specifications) {
        return and(Iterables.iterable(specifications));
    }

    public static <T> AndSpecification<T> and(final Iterable<Specification<T>> specifications) {
        return new AndSpecification<>(specifications);
    }

    public static <T> OrSpecification<T> or(final Specification<T>... specifications) {
        return or(Iterables.iterable(specifications));
    }

    public static <T> OrSpecification<T> or(final Iterable<Specification<T>> specifications) {
        return new OrSpecification<>(specifications);
    }

    public static <T> Specification<T> in(final T... allowed) {
        return in(Iterables.iterable(allowed));
    }

    public static <T> Specification<T> in(final Iterable<T> allowed) {
        return item -> {
            for (T allow : allowed) {
                if (allow.equals(item)) {
                    return true;
                }
            }
            return false;
        };
    }

    public static <T> Specification<T> notNull() {
        return item -> item != null;
    }

    public static <FROM, TO> Specification<FROM> translate(final Function<FROM, TO> function, final Specification<? super TO> specification) {
        return item -> specification.satisfiedBy(function.map(item));
    }

    public static class AndSpecification<T> implements Specification<T> {
        private final Iterable<Specification<T>> specifications;

        private AndSpecification(Iterable<Specification<T>> specifications) {
            this.specifications = specifications;
        }

        public boolean satisfiedBy(T instance) {
            for (Specification<T> specification : specifications) {
                if (!specification.satisfiedBy(instance)) {
                    return false;
                }
            }

            return true;
        }

        public AndSpecification<T> and(Specification<T>... specifications) {
            Iterable<Specification<T>> iterable = Iterables.iterable(specifications);
            Iterable<Specification<T>> flatten = Iterables.flatten(this.specifications, iterable);
            return Specifications.and(flatten);
        }

        public OrSpecification<T> or(Specification<T>... specifications) {
            return Specifications.or(Iterables.prepend(this, Iterables.iterable(specifications)));
        }
    }

    public static class OrSpecification<T> implements Specification<T> {
        private final Iterable<Specification<T>> specifications;

        private OrSpecification(Iterable<Specification<T>> specifications) {
            this.specifications = specifications;
        }

        public boolean satisfiedBy(T instance) {
            for (Specification<T> specification : specifications) {
                if (specification.satisfiedBy(instance)) {
                    return true;
                }
            }

            return false;
        }

        public AndSpecification<T> and(Specification<T>... specifications) {
            return Specifications.and(Iterables.prepend(this, Iterables.iterable(specifications)));
        }

        public OrSpecification<T> or(Specification<T>... specifications) {
            Iterable<Specification<T>> iterable = Iterables.iterable(specifications);
            Iterable<Specification<T>> flatten = Iterables.flatten(this.specifications, iterable);
            return Specifications.or(flatten);
        }
    }

}
