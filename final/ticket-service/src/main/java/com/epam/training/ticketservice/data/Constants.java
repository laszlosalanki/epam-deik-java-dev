package com.epam.training.ticketservice.data;

import com.epam.training.ticketservice.account.Account;
import java.util.List;

public class Constants {
    // Prompt
    public static final String PROMPT = "Ticket service>";

    // Date Time pattern
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

    // Break length in minutes
    public static final int BREAK_LENGTH = 10;

    // Log
    public static final String NO_MOVIES_AVAILABLE = "There are no movies at the moment";
    public static final String NO_ROOMS_AVAILABLE = "There are no rooms at the moment";
    public static final String NO_SCREENINGS_AVAILABLE = "There are no screenings";
    public static final String LOGIN_FAILED_INCORRECT_CREDENTIALS = "Login failed due to incorrect credentials";
    public static final String NOT_SIGNED_IN = "You are not signed in";
    public static final String SIGNED_IN_PRIVILEGED_ACCOUNT = "Signed in with privileged account '";
    public static final String NOT_SIGNED_IN_WITH_ADMIN_ROLE = "You are not an ADMIN";
    public static final String ALREADY_SIGNED_OUT = "Already signed out";
    public static final String SIGNED_OUT = "Signed out";
    public static final String UNAVAILABLE_COMMAND = "Your role is not " + Role.ADMIN;
    public static final String OVERLAPPING_SCREENING = "There is an overlapping screening";
    public static final String OVERLAPPING_BREAK = "This would start in the break period after another screening in this room";

    // Movie commands
    public static final String CREATE_MOVIE_METHOD_VALUE = "Create a new movie";
    public static final String CREATE_MOVIE_COMMAND = "create movie";

    public static final String UPDATE_MOVIE_METHOD_VALUE = "Update the given movie";
    public static final String UPDATE_MOVIE_COMMAND = "update movie";

    public static final String DELETE_MOVIE_METHOD_VALUE = "Delete the given movie";
    public static final String DELETE_MOVIE_COMMAND = "delete movie";

    public static final String LIST_MOVIES_METHOD_VALUE = "List the movies";
    public static final String LIST_MOVIES_COMMAND = "list movies";

    // Room commands
    public static final String CREATE_ROOM_METHOD_VALUE = "Create a new room";
    public static final String CREATE_ROOM_COMMAND = "create room";

    public static final String UPDATE_ROOM_METHOD_VALUE = "Update a given room";
    public static final String UPDATE_ROOM_COMMAND = "update room";

    public static final String DELETE_ROOM_METHOD_VALUE = "Delete the given room";
    public static final String DELETE_ROOM_COMMAND = "delete room";

    public static final String LIST_ROOMS_METHOD_VALUE = "List the rooms";
    public static final String LIST_ROOMS_COMMAND = "list rooms";

    // Screening commands
    public static final String CREATE_SCREENING_METHOD_VALUE = "Create a new screening";
    public static final String CREATE_SCREENING_COMMAND = "create screening";

    public static final String LIST_SCREENINGS_METHOD_VALUE = "List the screenings";
    public static final String LIST_SCREENINGS_COMMAND = "list screenings";

    public static final String DELETE_SCREENING_METHOD_VALUE = "Delete the given screening";
    public static final String DELETE_SCREENING_COMMAND = "delete screening";

    // Default accounts
    public static final List<Account> DEFAULT_ACCOUNTS = List.of(
            new Account("admin", "admin", Role.ADMIN)
    );

    // Account commands
    public static final String SIGN_IN_PRIVILEGED_METHOD_VALUE = "Sign in as a privileged user";
    public static final String SIGN_IN_PRIVILEGED_COMMAND = "sign in privileged";

    public static final String SIGN_OUT_METHOD_VALUE = "Sign out";
    public static final String SIGN_OUT_COMMAND = "sign out";

    public static final String DESCRIBE_ACCOUNT_METHOD_VALUE = "Describe account";
    public static final String DESCRIBE_ACCOUNT_COMMAND = "describe account";
}
