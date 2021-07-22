package ford.com.trackandartistinfo.controller;

import ford.com.trackandartistinfo.model.Track;
import ford.com.trackandartistinfo.service.MusicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/trackinfo")
@Api(value="Music Track Information System")
public class RestHandler {

    @Autowired
    MusicService musicService;


    @GetMapping(value = "/{region}", produces = "Application/json")
    @ApiOperation(value="View the top track in this region", notes = "${RestHandler.getTopTrack.notes}")
    @CrossOrigin(origins = {"http://localhost:4200"},
            maxAge = 4800, allowCredentials = "false")
    public Track getTopTrack(
            @ApiParam(value = "${RestHandler.getTopTrack.region}", required = true)
            @PathVariable("region") String region){

        try{
            return musicService.getTrackByRegion(region);
        }catch(JSONException je){
            System.out.println("hi exception");
            return null;
        }
    }

    @ApiOperation(value="return whatever string you've got.", notes = "Just trying something")
    @CrossOrigin(origins = {"http://localhost:4200"},
            maxAge = 4800, allowCredentials = "false")
    @GetMapping(value = "/nothing/{struct}", produces = "Application/json")
    public String returnString(
            @ApiParam(value = "dummy path variable", required = true)
            @PathVariable("struct") String struct
    ){
        return null;
    }
}
