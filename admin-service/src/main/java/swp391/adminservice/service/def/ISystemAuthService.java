package swp391.adminservice.service.def;

import swp391.adminservice.dto.request.AuthenticationRequest;
import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.AuthenticationResponse;

public interface ISystemAuthService {

    ApiResponse<AuthenticationResponse> authentication(AuthenticationRequest authRequest);

}
