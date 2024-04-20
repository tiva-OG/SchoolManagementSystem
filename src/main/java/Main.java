public class Main {

    public static void main(String[] args) {

        String associationTableName = "courses_students";
        String coursesTableName = "courses";

        String query = "SELECT * FROM %s c WHERE c.level<=? AND c.code NOT IN " +
                "(SELECT cs.course_code FROM %s cs WHERE cs.reg_number=?) " +
                "ORDER BY c.code;";
        query = String.format(query, coursesTableName, associationTableName);
        System.out.println(query);
    }
}
