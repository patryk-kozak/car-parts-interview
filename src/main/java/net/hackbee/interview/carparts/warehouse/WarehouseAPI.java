package net.hackbee.interview.carparts.warehouse;

import net.hackbee.interview.carparts.PartNotFoundException;

/**
 * This is artificial API to the Warehouse.
 * In normal conditions we would get this info via service or something like that.
 * For now Local one is enough. It will not be tested.
 */
public interface WarehouseAPI {

    PartStock checkStock(Long partId) throws PartNotFoundException;

}