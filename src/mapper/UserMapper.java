package mapper;

import model.Sex;
import model.User;
import service.validation.UserValidationRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserMapper {

    public static String toCsv(final User user) {
        // TODO can be replaced with UserCsvBuilder
        StringBuilder sb = new StringBuilder();
        sb.append(user.getId()).append(",");
        sb.append(user.getUserName()).append(",");
        sb.append(user.getPassword()).append(",");
        sb.append(user.getFirstName()).append(",");
        sb.append(user.getLastName()).append(",");
        sb.append(user.getBirthDate().format(DateTimeFormatter.ISO_LOCAL_DATE)).append(",");
        sb.append(user.getSex()).append(",");
        sb.append(user.getEmail());

        return sb.toString();
    }

    public static User toObject(final String csv) {
        String[] strings = csv.split(",");
        int i = 0;

        User user = new User();
        user.setId(Long.parseLong(strings[i++]));
        user.setUserName(strings[i++]);
        user.setPassword(strings[i++]);
        user.setFirstName(strings[i++]);
        user.setLastName(strings[i++]);
        user.setBirthDate(LocalDate.parse(strings[i++], DateTimeFormatter.ISO_LOCAL_DATE));
        user.setSex(Sex.valueOf(strings[i++]));
        user.setEmail(strings[i++]);

        return user;
    }

    public static User toObject(final UserValidationRequest request) {
        return new User(request.getUserName(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName(),
                LocalDate.parse(request.getBirthDate(), DateTimeFormatter.ISO_LOCAL_DATE),
                Sex.valueOf(request.getSex()),
                request.getEmail());
    }
}
