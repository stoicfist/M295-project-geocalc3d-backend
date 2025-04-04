package ch.ilv.m295.demoapp.dataaccess;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id, Class c) {
        super("Could not find " + c.getSimpleName() + " " + id);
    }
}