package arrayList;

import java.util.Comparator;

/**
 * Интерфейс ArrayList описывает методы для работы со структурой данных.
 *
 * @author Ляшенко Алина Эдурадовна
 * @see ArrayListImpl
 */
public interface ArrayList<T> {

    /**
     * Метод add используется для добавления элемента в ArrayList
     *
     * @param t добавляемый элемент
     */
    void add(T t);

    /**
     * Метод addByIndex используется для добавления элемента в ArrayList
     * по индексу, то есть в любое место данной структуры данных
     *
     * @param t     добавляемый элемент
     * @param index индекс, на позицию которого будет добавлен элемент
     */
    void addByIndex(T t, int index);

    /**
     * Метод get используется для получения элемента по его индексу
     *
     * @param index индекс элемента, который необходимо получить
     * @return T - метод возвращает элемент
     * @throws IndexOutOfBoundsException
     */
    T get(int index);

    /**
     * Метод remove используется для удаления элемента по индексу
     *
     * @param index индекс удаляемого элемента
     * @throws IndexOutOfBoundsException
     */
    void remove(int index);

    /**
     * Метод clear используется для удаления всех элементов
     */
    void clear();

    /**
     * Метод size используется для получения количества элементов
     * в ArrayList
     *
     * @return int - метод возвращает количество элементов
     */
    int size();

    /**
     * Метод toArray используется для преобразования ArrayList в
     * массив Object
     *
     * @return Object[] - метод возвращает массив
     */
    Object[] toArray();

    /**
     * Метод sort используется для сортировки структуры данных
     * Сортируемые объекты должны либо расширять интерфейс Comparable, либо
     * при использовать Comparator
     *
     * @param c Comparator для сравнения элементов
     */
    void sort(Comparator<? super T> c);

    void sort();
}
