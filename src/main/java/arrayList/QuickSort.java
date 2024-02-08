package arrayList;

import java.util.Comparator;

/**
 * Класс QuickSort используется для быстрой сортировки массива.
 *
 * @author Ляшенко Алина Эдурадовна
 * @see ArrayListImpl
 */
public class QuickSort {

    /**
     * Метод quickSort проверяет индексы и продолжает работу только в
     * том случае, если есть еще элементы, которые нужно отсортировать.
     * Рекурсионно вызывает сам себя для левой и правой частей массива.
     *
     * @param array массив который необходимо разделить
     * @param low   индекс, с которого необходимо начать сортировку
     * @param high  индекс, до которого необходимо начать сортировку
     * @param c     Comparator используется для корректного сравнения объектов
     * @return T[] - отсортированный массив.
     */
    static <T> T[] quickSort(T[] array, int low, int high, Comparator<? super T> c) {

        if (low < high) {
            int pi = partition(array, low, high, c);
            quickSort(array, low, pi - 1, c);
            quickSort(array, pi + 1, high, c);
        }
        return array;
    }

    public static Object[] quickSort(Object[] array, int low, int high) {

        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
        return array;
    }

    /**
     * Метод partition делит массив на 2 подмассива.
     * Элементы, которые меньше чем pivot (опорный) и элементы, которые больше чем pivot.
     * Опорным выбирается элемент индекс которого равен high.
     * <br><br>
     * Стратегия: после выбора опорного элемента, начинается просмотр с левого
     * конца массива, который продолжается до тех пор, пока не будет найден элемент, больший по
     * значению, чем опорный элемент. Затем выполняется просмотр, начиная с правого
     * конца массива, который продолжается до тех пор пока не будет найдет элемент, меньше опорного.
     * Далее эти 2 элемента меняются местами, так как они находятся не на своих позициях.
     *
     * @param array массив который необходимо разделить
     * @param low   индекс, с которого необходимо начать сортировку
     * @param high  индекс, до которого необходимо начать сортировку
     * @param c     Comparator используется для корректного сравнения объектов
     * @return int - позиция/медиана 2х подмассивов.
     */
    private static <T> int partition(T[] array, int low, int high, Comparator<? super T> c) {

        var pivot = array[high];

        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (c.compare(array[j], pivot) < 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private static int partition(Object[] array, int low, int high) {

        Comparable pivot = (Comparable) array[high];

        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (pivot.compareTo(array[j]) > 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    /**
     * Метод swap используется для перестановки двух
     * элементов между собой.
     *
     * @param array массив в котором необходимо поменять элементы местами
     * @param i     индекс первого меняемого элемента
     * @param j     индекс второго меняемого элемента
     */
    private static void swap(Object[] array, int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
