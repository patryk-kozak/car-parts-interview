package net.hackbee.interview.carparts;

// Checked exception to steer logic.
// If can, mitigate steering of logic to something else.
public class PartNotFoundException extends Exception {

    public PartNotFoundException(Long partId) {
        super(String.format("Part with id = %d has not been found.", partId));
    }
}
