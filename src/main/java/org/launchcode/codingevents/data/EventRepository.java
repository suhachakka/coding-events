package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**we learned that we can create a repository to fetch rows of a table in a database
 * a repository gets at the data in the table.
 *  Extending the CrudRepository interface gives us access to methods to perform
 *  all of the CRUD operations that we made happen in SQL
 **/
@Repository
public interface EventRepository extends CrudRepository<Event,Integer> {
}
//public class MyRepository implements EventRepository{}