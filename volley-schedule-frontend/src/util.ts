/**
 * Удаляет элемент из массива по ссылке на элемент
 * @param array
 * @param element
 */
export function removeByRef<T>(array: Array<T>, element: T) {
  if (array.includes(element)) {
    array.splice(array.indexOf(element), 1)
  }
  return array
}