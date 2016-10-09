package api;

/**
 * Interface representing a component that may be notified when a cell's
 * status changes.
 */
public interface CellObserver
{
  void update(Cell c);
}
