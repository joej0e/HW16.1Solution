package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.User;

import java.util.Optional;

public interface BucketDao {

    Optional<Bucket> get(Long id);

    Bucket getByUserId(Long id);

    Bucket update(Bucket bucket);

    void clear(Long id);

    Bucket create(User user);

}

