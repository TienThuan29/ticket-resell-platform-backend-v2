package swp391.adminservice.controller.def;

import swp391.adminservice.dto.request.AuthenticationRequest;
import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.AuthenticationResponse;

public interface ISystemAuthController {

    ApiResponse<AuthenticationResponse> authentication(AuthenticationRequest authRequest);

}
