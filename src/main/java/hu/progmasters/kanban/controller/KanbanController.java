package hu.progmasters.kanban.controller;

import hu.progmasters.kanban.domain.SprintState;
import hu.progmasters.kanban.dto.*;
import hu.progmasters.kanban.security.AuthenticatedUserDetails;
import hu.progmasters.kanban.service.KanbanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/kanban")
public class KanbanController {
    private static final Logger logger = LoggerFactory.getLogger(KanbanController.class);

    private KanbanService kanbanService;

    @Autowired
    public KanbanController(KanbanService kanbanService) {
        this.kanbanService = kanbanService;
    }

    @PostMapping("/sprints")
    public ResponseEntity<Long> createOrUpdateSprint(@RequestBody SprintCommand sprintCommand) {
        Long sprintId = kanbanService.createSprint(sprintCommand);
        logger.info("Sprint {} (id: {})", sprintCommand.getId() == null ? "created" : "updated", sprintId);
        return new ResponseEntity<>(sprintId, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SprintDetails>> provideSprintList() {
        logger.info("SprintView requested");
        return new ResponseEntity<>(kanbanService.getSprints(), HttpStatus.OK);
    }

    @GetMapping("/sprints/states")
    public ResponseEntity<List<String>> provideSprintStateOptions() {
        logger.info("Sprint state options requested");
        return new ResponseEntity<>(Arrays.stream(SprintState.values()).map(SprintState::getDisplayName).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/stories/{sprintId}")
    public ResponseEntity<List<UserStoryDetails>> provideStoriesBySprintId(@PathVariable Long sprintId) {
        logger.info("UserStories requested by SprintId (id: {})", sprintId);
        return new ResponseEntity<>(kanbanService.getUserStoriesBySprintId(sprintId), HttpStatus.OK);
    }

    @GetMapping("/sprints/{sprintId}")
    public ResponseEntity<SprintExtDetails> provideSprintDetailsBySprintId(@PathVariable Long sprintId) {
        logger.info("Sprint detailed info requested (id: {})", sprintId);
        return new ResponseEntity<>(kanbanService.getSprintExtendedDetails(sprintId), HttpStatus.OK);
    }

    @PostMapping("/sprints/delete")
    public ResponseEntity<HttpStatus> deleteSprint(@RequestBody Long sprintId) {
        logger.info("Sprint delete requested (id: {})", sprintId);
        kanbanService.deleteSprint(sprintId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createOrUpdateUserStory(@RequestBody UserStoryCommand userStoryCommand) {
        Long userStoryId = kanbanService.createUserStory(userStoryCommand);
        logger.info("UserStory {} (id: {})", userStoryCommand.getId() == null ? "created" : "updated", userStoryId);
        return new ResponseEntity<>(userStoryId, HttpStatus.OK);
    }

    @PostMapping("/updateState")
    public ResponseEntity<HttpStatus> updateUserStoryState(@RequestBody UserStoryStateUpdateCommand userStoryStateUpdateCommand) {
        logger.info("UserStory state update requested (id: {})", userStoryStateUpdateCommand.getUserStoryId());
        kanbanService.updateUserStoryState(userStoryStateUpdateCommand);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<HttpStatus> deleteUserStory(@RequestBody Long userStoryId) {
        logger.info("UserStory delete requested (id: {})", userStoryId);
        kanbanService.deleteUserStory(userStoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/auth")
    public ResponseEntity<AuthenticatedUserDetails> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();

        return new ResponseEntity<>(new AuthenticatedUserDetails(user), HttpStatus.OK);
    }
}
