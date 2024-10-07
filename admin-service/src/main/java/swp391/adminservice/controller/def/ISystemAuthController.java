package swp391.adminservice.controller.def;

import swp391.adminservice.dto.request.AuthenticationRequest;
import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.AuthenticationResponse;
import swp391.adminservice.dto.response.StaffDTO;

public interface ISystemAuthController {

    ApiResponse<StaffDTO> getInfoByToken(String token);

    ApiResponse<AuthenticationResponse> authentication(AuthenticationRequest authRequest);

    ApiResponse<AuthenticationResponse> refreshToken(String refreshToken);

}
