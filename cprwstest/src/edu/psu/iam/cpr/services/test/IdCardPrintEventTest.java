package edu.psu.iam.cpr.services.test;

/**
 * Copyright 2012 The Pennsylvania State University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import org.testng.annotations.Test;
import edu.psu.iam.cpr.service.Cprws;
import edu.psu.iam.cpr.service.CprwsSEI;
import edu.psu.iam.cpr.service.IdCardPrintEventServiceReturn;
import edu.psu.iam.cpr.service.IdCardServiceReturn;
import edu.psu.iam.cpr.service.ServiceReturn;


public class IdCardPrintEventTest {

	byte[] testImage = new byte[] {
			-119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0, 0, 16, 0, 0, 0, 12, 8, 2, 0, 0, 0, -28, 
			-123, -86, -42, 0, 0, 22, 90, 105, 67, 67, 80, 73, 67, 67, 32, 80, 114, 111, 102, 105, 108, 101, 0, 0, 88, -123, -75, -104, 9, 52, 
			-43, -33, -73, -64, -49, -9, -50, -125, 107, -98, -25, 121, -98, -25, 121, -98, 101, 38, 115, -26, 121, -56, 76, 40, 66, -118, -120, -52, 83, 19, 
			50, 36, 42, 74, -120, -110, 49, -123, -94, -112, 66, 36, 99, 72, 9, 33, 121, -73, 122, -11, -5, -65, -75, -34, 127, -67, -11, -42, 122, 111, 
			-81, 117, -18, -3, -84, -67, -50, -35, -33, 115, -65, 123, -97, -77, -9, 62, 0, -48, 23, -70, 6, 7, 7, -64, -56, 1, 8, 12, 10, 15, 
			-75, -48, -45, 100, -73, -75, -77, 103, 71, 79, 2, 8, -64, 1, 10, -80, 1, 38, 87, -9, -80, 96, 13, 51, -77, 35, -32, -33, -54, -10, 
			24, 113, 54, 81, 94, -118, -4, -76, -11, -17, -25, -3, -73, 66, -31, -31, 25, -26, 14, 0, 100, 70, 100, 55, -113, 48, -9, 64, 34, -33, 
			37, -14, -122, 123, 112, 104, 56, 0, -80, 10, 34, -65, -120, 10, 15, 38, 50, -100, 56, 0, 117, 40, 113, -127, 68, 62, -9, -109, -67, 127, 
			115, -23, 79, 118, -5, -51, -73, 127, -51, -79, -78, -48, 34, 114, 55, 0, 24, 18, 87, -41, 80, 111, 0, -16, 47, -120, 122, -10, 72, 119, 
			111, -94, 13, -4, 6, 0, 40, -54, 32, 15, -33, 32, 0, 40, 17, 68, 86, 117, -9, 113, -11, 0, -128, 94, -101, 56, 71, 56, 48, -16, 
			-8, 79, 14, 38, 50, -65, -37, -65, -40, -15, -2, 47, 54, -35, -2, -38, 116, 117, -11, -2, -53, -65, -1, -53, 47, -63, 104, -5, -122, 5, 
			7, -72, -98, -8, 95, -66, -114, -1, 89, 2, 3, 34, -2, 60, -125, -110, 56, 72, -126, 2, 76, 126, -6, 6, 67, 28, 75, 30, -82, -38, 
			70, 127, 56, 56, -64, -20, -81, -34, 51, -56, -38, -14, 15, 7, -71, -103, -104, -2, 97, -81, 80, 93, -117, -65, -13, -61, 53, -1, -123, -51, 
			-84, -2, 112, -116, -113, -106, -55, 95, 59, 97, 58, 127, -19, -8, -71, 26, -102, -3, -31, -48, 8, 11, -21, 63, 28, 22, 105, -87, -13, -49, 
			111, -83, 108, -2, -82, -51, 83, -5, -81, -34, -53, 87, -41, -32, 15, -5, -122, 27, -4, 125, -106, -1, 113, -93, -65, 107, 0, -66, -64, 24, 
			-72, 2, -9, 112, -49, -24, -97, 126, 7, 90, -57, -125, 79, -124, -6, 122, -5, -124, -77, 107, 16, -93, -52, 83, -104, -35, 32, -56, 93, 84, 
			-104, 93, 82, 92, 66, -14, -1, -4, 29, -1, 127, -54, -49, -3, -11, -101, 54, 45, 126, -19, 27, -120, -10, -7, 63, -70, 120, 106, 0, 52, 
			19, -120, -54, -85, -1, -24, -84, 110, 0, 80, -29, 72, -116, -55, 35, -1, -24, -72, -24, -119, 97, -50, 13, 64, -117, -122, 123, 68, 104, -28, 
			111, 29, -30, -25, 7, 18, -32, 0, 25, -96, 6, 12, -128, 21, 112, 1, 126, 32, 2, 36, -127, 44, 80, 2, -22, 64, 7, 24, 2, 83, 
			96, 5, -20, -64, 49, -32, 14, 124, 64, 32, 8, 5, 81, 32, 14, -100, 6, 41, -32, 60, -56, 2, 121, -96, 24, 92, 1, -27, -96, 26, 
			-44, -127, 6, 112, 31, -76, -127, -57, -96, 23, 60, 3, 47, -64, 40, 120, 11, 102, -64, 60, -8, 8, 54, -64, 54, -40, -121, 32, 8, 13, 
			17, 32, 42, -120, 1, 98, -125, 120, 32, 33, 72, 18, -110, -121, 84, 33, 29, -24, 8, 100, 1, -39, 65, 46, -112, 55, 20, 4, 69, 64, 
			113, 80, 18, 116, 30, -54, -122, -118, -95, 107, 80, 13, 116, 7, 106, -127, 30, 67, -3, -48, 48, 52, 1, -51, 66, -53, -48, 23, 104, 15, 
			6, -121, -111, -64, -88, 97, 44, 48, 94, -104, 24, 76, 30, -90, 1, 51, -126, 89, -63, -100, 96, -34, -80, 16, 88, 12, 44, 25, 118, 1, 
			86, 8, 43, -125, -35, -124, 53, -63, 30, -61, -98, -63, 70, 97, 51, -80, -113, -80, 45, 56, -128, -29, -31, -76, 112, 14, -72, 8, 92, 30, 
			-82, 5, 55, -123, -37, -61, -67, -32, -95, -16, 83, -16, 52, 120, 62, -68, 12, 94, 15, 111, -123, -9, -63, 95, -62, 103, -32, -85, -16, 93, 
			4, 10, 65, -123, 96, 71, -120, 32, -108, 16, -6, 8, 107, -124, 59, 34, 4, 113, 10, -111, -114, 40, 70, 84, 35, -102, 16, -35, -120, -105, 
			-120, 89, -60, 6, -30, 7, -110, -128, 100, 70, 10, 33, 21, -111, 6, 72, 91, -92, 55, 50, 10, -103, -126, -52, 71, 86, 34, -17, 33, 123, 
			-112, -93, -56, 121, -28, 54, 10, -123, -94, 69, -15, -95, -28, 80, -6, 40, 59, -108, 31, 42, 22, -107, -114, -70, -124, -70, -123, -22, 64, 13, 
			-93, -26, 80, 91, 104, 52, -102, 1, 45, -124, 86, 65, -101, -94, 93, -47, -31, -24, 20, 116, 17, -6, 38, -6, 17, 122, 4, 61, -113, -2, 
			-122, -63, 99, -40, 48, -110, 24, 93, -116, 61, 38, 8, -109, -120, -55, -57, -36, -64, 60, -60, -116, 96, 22, 49, -5, 88, 114, 44, 15, 86, 
			17, 107, -118, -11, -64, -98, -64, 102, 98, 43, -80, -83, -40, -25, -40, 121, -20, 62, -114, 2, -57, -121, 83, -63, 89, -31, -4, 112, -89, 113, 
			-123, -72, 122, 92, 15, 110, 18, -73, -119, -57, -29, 57, -15, 10, 120, 115, -68, 47, 62, 1, 95, -120, -65, -115, 127, -126, -97, -59, -17, -110, 
			80, -110, 8, -110, 104, -111, 56, -110, 68, -112, 92, 32, -87, 34, -23, 32, -103, 32, -39, 36, 16, 8, -68, 4, 117, -126, 61, 33, -100, 112, 
			-127, 80, 67, -24, 34, 76, 19, -66, -111, 82, -111, -118, -110, 26, -112, 122, -112, -58, -109, -106, -112, 54, -111, -114, -112, -82, -109, 97, -55, 120, 
			-56, 52, -56, -114, -111, -59, -112, -27, -109, 53, -110, 61, 39, 91, 37, -57, -110, -13, -110, 107, -111, -69, -110, -97, 34, 47, 33, 111, 33, 31, 
			39, -33, -94, -96, -94, -112, -96, 48, -91, 8, -92, 72, -89, -72, 65, -47, 79, -79, 68, -119, -90, -28, -91, -44, -95, -12, -96, 76, -90, 44, 
			-89, -20, -94, -100, -93, -126, 83, 113, 81, 105, 81, -71, 83, 37, 81, 85, 80, -11, 80, -51, 83, -93, -88, -7, -88, 13, -88, -3, -88, -49, 
			83, -41, 81, 15, 81, 111, -48, 80, -46, 72, -45, 28, -91, -119, -90, 41, -95, 105, -89, -103, -95, -123, -45, -14, -46, 26, -48, 6, -48, 102, 
			-46, 54, -48, -114, -47, -18, -47, -79, -48, 105, -48, 121, -46, -91, -46, -43, -45, -115, -48, -19, -48, 51, -47, -85, -45, 123, -46, -89, -47, -33, 
			-94, 31, -91, -33, 99, 96, 103, -48, 97, -16, 103, -72, -56, 112, -97, 97, -118, 17, -63, 40, -56, 104, -50, 24, -59, 120, -103, -79, -121, 113, 
			-107, -119, -102, 73, -119, -55, -99, 41, -115, -87, -127, -23, 13, 51, -116, 89, -112, -39, -126, 57, -106, -71, -100, 121, -128, 121, -117, -123, -107, 69, 
			-113, 37, -104, -91, -120, -91, -117, 101, -107, -107, -106, 85, -99, -43, -113, 53, -105, -11, 33, -21, 50, 27, 21, -101, 42, -101, 47, 91, 46, -37, 
			35, -74, 21, 118, 26, 118, 13, -10, 0, -10, 66, -10, 110, -10, 13, 14, 102, 14, 125, -114, 8, -114, 107, 28, 67, 28, -5, -100, 124, -100, 
			-42, -100, -119, -100, -73, 56, -89, -72, 112, 92, -14, 92, 94, 92, -71, 92, -99, 92, 27, -36, 108, -36, -58, -36, 113, -36, -75, -36, 111, 120, 
			-80, 60, -14, 60, 62, 60, 5, 60, 125, 60, 59, -68, 124, -68, 54, -68, 103, 121, -17, -13, 46, -15, -47, -13, 25, -16, -59, -16, -43, -14, 
			77, -14, 19, -8, -43, -8, 67, -8, -53, -8, 95, 9, -96, 4, -28, 5, -4, 5, 46, 9, -68, 16, -124, 9, -54, 8, -6, 8, -106, 8, 
			62, 23, -126, 9, -55, 10, -7, 10, 93, 18, 26, 22, 70, 10, 43, 8, 7, 9, -105, 9, -113, -117, -112, -120, 104, -120, 68, -118, -44, -118, 
			-52, -118, -46, -118, 30, 17, 77, 20, -67, 47, -70, 46, -58, 45, 102, 47, 118, 81, -84, 79, -20, -121, -72, -116, 120, -128, 120, -123, -8, 91, 
			9, 74, 9, 67, -119, 68, -119, 86, -119, 47, -110, -126, -110, -18, -110, 37, -110, -81, -92, 8, 82, -70, 82, -15, 82, -51, 82, -97, -91, -123, 
			-92, 61, -91, 47, 75, -65, -106, -95, -110, 49, -106, 57, 43, -45, 41, 115, 32, 43, 39, 27, 42, 91, 47, -69, 44, -57, 45, -25, 34, 87, 
			42, 55, 46, 79, 45, 111, 38, -97, 46, -1, 68, 1, -87, -96, -87, 16, -81, -48, -90, -80, -85, 40, -85, 24, -82, -40, -96, -8, 73, 73, 
			68, -55, 95, -23, -122, -46, -110, 50, -97, -78, -89, 114, -123, -14, -100, 10, -89, -118, -85, -54, 53, -107, 25, 85, 118, 85, 23, -43, -85, -86, 
			51, 106, 28, 106, -82, 106, 101, 106, -17, -43, -71, -44, 61, -44, 43, -43, 23, 53, 4, 52, -4, 52, 110, 106, -84, 107, -118, 107, -122, 106, 
			-34, -45, -36, -47, 82, -44, 58, -87, -43, -95, 13, -41, -42, -45, 78, -45, 30, -46, -95, -44, -79, -42, 41, -42, -103, -42, -27, -44, -11, -42, 
			-83, -43, -35, -48, -109, -47, -117, -43, -21, -48, 71, -22, 27, -23, 95, -44, 31, 55, 96, 49, 112, 55, -88, 49, -40, 48, -108, 51, 60, 105, 
			-40, 109, 68, 98, 100, 105, 84, 108, -12, -2, -120, -32, -111, -48, 35, -83, -58, 48, 99, 67, -29, 28, -29, 73, 19, 30, -109, 32, -109, -5, 
			-90, -64, -44, -64, 52, -57, 116, -54, -116, -49, 44, -60, -20, -127, 57, -54, -36, -52, -68, -60, 124, -63, 66, -62, 34, -50, -94, -49, -110, -54, 
			-46, -39, -14, -122, -27, -74, -107, -90, 85, -90, -43, 91, 107, 126, -21, 8, -21, -50, -93, 100, 71, 29, -113, -42, 28, -35, -79, -47, -74, -55, 
			-74, -103, -79, 21, -77, 61, 105, -5, -52, -114, -47, -50, -41, -82, -39, 30, 109, 127, -44, -66, -46, 126, -53, 65, -57, 33, -49, 97, -34, 81, 
			-58, 49, -59, 113, -52, -119, -49, 41, -38, -87, -1, 24, -29, -79, -128, 99, -19, -50, 100, -50, -82, -50, -115, 46, 72, 23, 27, -105, 27, 46, 
			-33, 93, 77, 93, -53, 92, -73, -36, 12, -36, 74, -35, 54, -36, -75, -36, 11, -36, 63, 122, -88, 123, -28, 122, 44, 123, -86, 120, 102, 123, 
			46, 122, -87, 120, 101, 123, 45, 121, -85, 120, -25, 120, 47, -5, -88, -7, -28, -5, -84, -6, 106, -7, 22, -5, 126, -10, -45, -9, -69, -30, 
			-73, -29, 111, -22, 95, -27, 127, 24, 96, 19, 112, 43, 16, 19, -24, 18, -40, 18, 68, 25, -28, 31, -44, 125, -100, -11, 120, -12, -15, -31, 
			96, -95, -32, -108, -32, -103, 16, -59, -112, -68, -112, -115, 80, -93, -48, -54, 48, 40, -52, 41, -84, 57, -100, -102, 88, -56, 12, 68, -16, 71, 
			-100, -119, -104, -115, 84, -115, 44, -119, -4, 22, 117, 52, -86, 49, -102, 34, 58, 40, 122, -32, -124, -32, -119, -44, 19, -117, 49, -70, 49, -41, 
			99, 17, -79, -18, -79, -99, 113, 28, 113, -89, -29, 102, 79, 106, -100, -68, 118, 10, 58, -27, 118, -86, 51, -98, 43, 62, 57, 126, 62, 65, 
			47, -95, -6, 52, -18, -76, -1, -23, -63, 68, -15, -60, -20, -60, -81, 73, 54, 73, -83, -55, 44, -55, 9, -55, 115, 103, -12, -50, -44, -90, 
			-112, -90, -124, -90, -116, -97, 85, 58, 123, -27, 28, -30, -100, -17, -71, -95, 84, -87, -44, -94, -44, 31, 105, 30, 105, 79, -49, -117, -97, -49, 
			63, -1, 61, -35, 61, -3, 105, -122, 68, 70, 97, -58, -31, 5, -81, 11, 67, -103, -78, -103, -105, -77, 80, 89, 65, 89, 99, 23, -43, 46, 
			86, 103, 83, 100, -57, 100, -49, -27, 24, -25, 52, -27, -78, -25, -90, -27, 126, -51, 115, -50, -21, -49, -105, -50, -65, 82, -128, 43, -120, 40, 
			-104, 41, 60, 82, -40, 92, -60, 93, -108, 85, -12, -67, -40, -89, 120, -76, 68, -77, -28, 86, 41, 115, 105, 106, -23, -50, 37, -113, 75, 35, 
			-105, -43, 47, -41, 95, 97, -71, 114, -2, -54, -34, 85, -33, -85, -81, -81, -23, 93, 107, 42, -29, 45, -53, 47, 71, -107, 71, -106, 47, 84, 
			28, -83, -24, -69, 46, 127, -67, -90, -110, -79, -14, 124, -27, 65, 85, 80, -43, 76, -75, 69, 117, 119, -115, 92, 77, -51, 13, -26, 27, -103, 
			-75, -80, -38, -120, -38, -27, -101, -114, 55, 95, -44, 105, -41, 53, -41, -117, -44, 95, -69, 69, 123, -21, -4, 109, 112, 59, -30, -10, -54, 29, 
			-105, 59, 99, 13, 70, 13, -99, -115, -14, -115, -11, 119, 121, -18, -106, -34, -93, -70, -105, -42, 4, 53, -99, 104, -38, -72, -17, 115, 127, -90, 
			-39, -82, 121, -72, -59, -80, -91, -77, 85, -87, -11, -34, 3, -47, 7, 85, 109, 28, 109, 37, -19, 52, -19, -103, 15, 113, 15, -109, 31, 30, 
			62, -118, 121, -76, -43, 17, -36, -79, -6, -40, -5, -15, 92, -89, 115, -25, -37, 46, -37, -82, 87, -35, -26, -35, 67, 61, 70, 61, 79, 122, 
			117, 123, -69, -6, 52, -6, 30, 61, 81, 121, -46, -42, -81, -40, -33, -14, 84, -2, -23, -3, 103, -78, -49, -102, 6, 100, 6, -18, 13, -54, 
			12, -34, 27, -110, 29, 106, 122, 46, -9, -68, -7, -123, -62, -117, -42, 97, -27, -31, -121, 35, 106, 35, -113, 95, 106, -65, -20, 125, 101, -16, 
			-22, -39, -88, -55, -24, -16, -104, -11, -40, -21, 113, -57, -15, -103, -41, 30, -81, -105, 38, 2, 38, 62, -65, -119, 124, -77, -1, 54, 97, 18, 
			57, -103, 54, 69, 62, -107, 63, -51, 60, 93, -10, 78, -32, -35, -83, 25, -39, -103, -10, 89, -19, -39, -127, -9, -106, -17, -33, -50, -71, -49, 
			125, -4, 16, -10, -31, -5, 124, -14, 2, 97, 33, 127, -111, 109, -79, 102, 73, 114, -87, 109, 89, 119, -7, -59, -118, -61, -54, -4, -57, -32, 
			-113, -5, -85, 41, 107, 20, 107, -91, -21, -4, -21, 119, 63, -87, 127, 26, -40, -80, -35, -104, -1, 28, -6, -7, -16, 75, -6, 38, -61, 102, 
			-43, 87, -23, -81, -99, 91, 102, 91, -45, -37, -127, -37, -5, 59, 105, -33, 24, -66, 85, -17, -54, -17, -10, -19, -39, -20, 45, -18, 71, 125, 
			71, 127, 47, 60, 16, 56, 104, -3, 97, -12, 99, -14, 48, -16, -16, 48, -40, 53, -44, -11, 87, 41, 0, 39, 14, -104, -105, 23, 0, 95, 
			-86, 0, 32, -40, 1, 64, 69, -84, 79, 113, -92, -65, -21, -33, -1, 20, 56, -79, -8, -128, 17, -65, -123, 64, 55, 49, -125, 103, -63, -80, 
			48, 123, 88, 27, 92, 0, 94, -124, -96, 65, 100, 34, -87, -111, -7, 40, 46, 84, 43, -38, 6, -3, 29, 83, -115, 117, -64, 81, -31, -58, 
			-15, -27, 36, 17, 4, 51, 82, 25, 50, 118, 114, 90, 10, 60, 37, -114, -118, -108, -102, -123, 70, -108, 86, -115, -50, -114, 62, -122, -95, -128, 
			-79, -107, 105, -98, -123, -98, -43, -100, 45, -127, -3, 14, -57, 34, 23, 27, -73, 13, 79, 22, -17, 19, -66, 31, 2, -14, -126, -2, 66, -27, 
			-62, -109, -94, -28, 98, -70, -30, -89, 37, -102, 37, -41, -92, -7, 101, -20, 101, 51, -27, -6, 21, 16, -118, -86, 74, 17, -54, 119, 84, -42, 
			-44, 120, -43, 101, 53, -92, 52, 37, -75, -60, -75, 5, 117, -8, 116, 121, -11, 120, -11, -71, 12, 120, 13, 121, -116, -8, -114, -16, 25, -13, 
			-103, 8, -101, -118, -103, 73, -101, 43, 88, -88, 88, -86, 89, -23, 90, -101, 28, -75, -78, 113, -80, 117, -73, 11, -80, -113, 116, 56, -23, 120, 
			-54, -23, -52, -79, -13, -50, 23, 93, 10, 92, 47, -71, 93, 119, -81, -13, -72, -17, -39, -31, 53, -24, 61, -23, -77, -20, -69, -21, 79, 8, 
			-32, 8, -108, 9, 50, 62, -18, 21, -100, 20, 114, 53, -12, 97, -40, 84, 4, -120, 36, -117, 34, 68, 35, -94, -65, -97, -40, -118, 89, -113, 
			93, -120, -101, 62, 57, 118, 106, 40, -66, 43, -95, -19, 116, 67, 98, 117, 82, 89, 114, -2, -103, -84, -108, -28, -77, -79, -25, 66, 82, -3, 
			-46, -36, -50, -37, -90, -101, 103, -40, 92, 72, -50, -20, -56, -38, -50, 22, -49, -15, -49, -67, -98, 55, 93, 64, 91, 104, 90, -108, 86, -36, 
			93, 10, 93, 82, -67, 28, 115, -91, -23, -22, 78, -103, 124, 121, 108, -59, -93, 74, 84, -107, 105, 117, 110, -51, -101, 27, 91, -75, -69, 117, 
			80, 61, -18, 22, -39, 109, -38, 59, 44, 13, 92, -115, 66, 119, -91, -18, -87, 52, -23, -34, 55, 105, -74, 105, 113, 107, 13, 124, 16, -43, 
			-106, 68, -116, -46, -62, 71, 101, 29, -11, -113, 91, 58, -69, -70, -98, 116, 63, -17, 121, -43, 59, -43, -73, -8, -28, 115, -1, -18, 51, -52, 
			0, -51, 32, -41, -112, -20, 115, -93, 23, -50, -61, 81, 35, 23, 95, -42, 17, 99, 110, 117, -100, -30, -75, -52, -124, -2, 27, -43, -73, -68, 
			-109, 36, -109, 95, -90, 94, 79, 63, 124, 87, 54, -109, 50, -21, -5, -34, 112, 78, -24, 3, -31, -61, -22, 124, -1, 66, -27, 98, -30, -46, 
			-79, 101, -59, 21, -102, -107, -11, -113, 125, -85, -41, -42, 98, -42, 45, 63, -119, 110, -96, 55, 102, 63, -73, 125, 41, -39, -116, -7, -22, -72, 
			-91, -66, -51, -73, 67, -11, 13, -2, 109, 103, -9, -53, -34, -50, -2, -113, 3, -54, 31, 34, -121, 50, -121, -121, -65, 98, -123, 20, 112, 18, 
			-21, 67, 95, 112, 9, 76, 65, -20, -112, 7, -44, 64, -116, 5, 127, -40, 107, -72, 25, 124, 12, -31, -117, -60, 32, 111, -95, 60, -48, 124, 
			-24, 13, 76, 47, -74, 10, 119, 17, -97, 68, 18, 79, -120, 39, 61, 75, 86, 72, 126, -109, -94, -117, -14, 13, -43, 119, 26, 42, 90, 89, 
			58, 107, -6, 96, -122, 98, -58, 1, -90, 67, 22, 73, 86, 39, -74, 12, -10, -121, 28, 27, 92, 92, -36, 38, 60, -79, -68, -107, 124, 3, 
			-4, 91, -126, 76, 66, 74, -62, 118, 34, 49, -94, -7, 98, 117, -30, 93, 18, 99, -110, -117, 82, -5, 50, 4, 89, 22, 57, 65, 121, 101, 
			5, 125, 69, 11, 37, 3, 101, 25, 21, 78, 85, 82, -43, 93, -75, 121, -11, 81, -115, 46, -51, 59, 90, -41, -76, -77, 117, -110, 116, 35, 
			-12, -68, -12, -19, 13, 76, 12, -75, -116, -28, -113, 8, 27, -77, -101, -48, -101, -110, -103, 33, -51, -66, -101, 111, 89, 124, -78, 92, -74, 122, 
			111, 61, 121, -12, -115, -51, 107, -37, 81, -69, 97, -5, 33, -121, 1, -57, 126, -89, -66, 99, -35, -50, -35, 46, 61, -82, -35, 110, 93, -18, 
			61, 30, 125, -98, 125, 94, 79, -67, -121, 124, 94, -6, -66, -15, 123, -25, 63, 31, -80, 22, -72, 18, -12, -10, -8, -45, -32, -26, -112, -86, 
			-48, -68, -80, -28, -16, -32, 8, -105, 72, -29, 40, -71, 104, -98, 19, -108, 39, 126, -60, -84, -60, -114, -57, -11, -100, -68, 125, -86, 36, 62, 
			53, 33, -22, -76, 103, -94, 69, -110, 70, -78, -54, 25, -85, -108, -104, -77, 69, -25, -38, 82, -89, -49, 67, -23, -100, 25, 26, 23, -36, 51, 
			-49, 102, -43, 92, 124, -98, -3, 53, -105, 49, 79, 35, 63, -96, -96, -88, -80, -81, -24, 91, -119, 72, -87, -57, -91, -126, -53, -61, 87, -74, 
			-81, -111, -107, -119, -108, 31, -87, -16, -70, 126, -90, -14, 106, 85, 107, -11, 88, -51, 102, 45, -39, 77, -95, 58, -19, -6, 99, -73, -94, 110, 
			-25, -36, -87, 105, -24, 105, 124, 119, -9, 107, 19, -18, 62, 71, -77, 66, -117, 69, -85, -17, -125, -124, -74, -126, -10, -37, 15, -97, 62, -6, 
			-48, 113, -48, -55, -36, -91, -40, 109, -37, 19, -41, 123, -83, -81, -25, -55, -89, -89, -116, -49, -12, 6, -94, 6, 111, 12, -51, -66, -96, 31, 
			-42, 29, -15, 126, 121, -6, 85, -15, 104, -61, -40, -64, -8, -4, -21, -3, 55, 84, 111, 5, 39, -43, -90, -84, -90, 125, -33, -59, -50, 100, 
			-52, 94, 122, 95, 55, -9, -16, -61, -64, -4, -44, -62, -14, -30, -42, -46, -18, -14, -105, -107, -75, -113, 75, -85, -77, 107, -45, -21, -109, -97, 
			-90, -119, -89, -55, -6, -105, -3, -81, -28, 91, -94, -37, -26, 59, 39, -65, -35, -38, 93, -35, 23, -5, 30, 123, 48, 114, -56, -12, -53, -1, 
			40, -64, 12, -76, -119, 93, -64, 75, -120, 3, -14, -127, -38, 97, -116, -80, 104, -40, 44, -79, -78, -18, 65, 104, 33, -98, 32, -51, -111, -77, 
			-88, 80, 52, 14, 93, -127, -47, -61, 124, -58, 22, -32, -76, 112, 91, -8, 42, -110, 99, 4, 38, -62, 4, 105, 33, -103, 29, 57, 35, -7, 
			20, 69, 25, -91, 47, -107, 56, -43, 30, 117, 39, 77, 54, -83, 39, -99, 44, 61, -118, -2, 13, -61, 109, -58, 52, 38, 79, 102, 21, 22, 
			6, -106, 109, -42, -105, 108, 77, -20, -123, 28, 49, -100, 46, 92, 70, -36, -78, 60, 108, -68, -92, -68, -69, 124, 75, -4, -109, 2, -61, -126, 
			125, 66, -113, -124, 91, 68, -18, -118, -106, -119, 69, -120, -117, -118, -65, -105, -56, -105, 52, -112, 60, -112, 106, -112, -10, -106, 97, -106, 25, -111, 
			77, -107, 83, -105, -37, -106, -81, 83, -16, 80, 100, 81, 28, 83, -54, 84, -42, 83, 62, 84, 105, 85, -115, 80, 19, 85, 91, 82, 47, -41, 
			112, -47, 100, -47, -100, -42, -86, -48, 14, -44, -111, -41, -123, -23, 14, -22, 21, -23, -5, 24, 40, 24, -30, 12, -89, -115, -102, -114, 92, 52, 
			14, 48, 49, 50, 21, 53, -93, 54, -37, 39, 86, 17, -81, 44, 123, -84, -102, -83, -21, -114, -106, -37, 20, -37, 38, -40, 25, -39, -109, -39, 
			-65, 114, 40, 114, 116, 118, -30, 113, -6, 120, -20, -114, 115, -76, -117, -78, 43, -62, -11, -103, 91, -74, -69, -93, 7, -105, -57, 71, -49, 70, 
			-81, 88, 111, 109, 31, 50, -97, 9, -33, 50, -65, 64, 127, -7, 0, 100, -64, 72, 96, 89, 80, -40, 113, -99, 96, -122, -32, -11, -112, -18, 
			-48, 75, 97, 49, -31, -74, 17, 74, -111, -20, 81, -24, -88, 47, -47, 51, 39, 70, 98, 122, 99, -37, -29, -102, 79, -34, 59, -43, 16, 127, 
			59, -31, -6, -23, -116, -60, -72, -92, -128, 100, -69, 51, 6, 41, -14, 103, 121, -49, -47, -91, 98, 83, 15, -46, 62, -97, -97, 75, 31, -49, 
			24, -68, -48, -99, -39, -102, 117, -9, 98, 125, 118, 69, 78, 105, 110, 94, 94, 118, -2, -51, -126, -114, -62, -119, -94, -115, 18, 66, -87, -48, 
			37, -93, -53, -127, 87, -14, -81, 118, 92, 91, 43, -25, -84, -80, -65, -98, 91, 57, 90, -51, 80, -29, 118, -93, -66, -10, -80, -50, -86, -66, 
			-10, -42, -26, 29, -18, 6, -93, -58, -112, -69, -59, -9, 122, -101, -10, -102, -59, 90, 2, 91, 111, 62, -40, 106, -41, 121, -104, -9, 104, -3, 
			-79, 97, -25, -51, 110, -110, -98, -120, -34, -87, 39, -122, -3, 15, -98, -15, 13, 20, 12, -31, -97, -97, 121, 113, 48, 18, -9, 114, 99, 52, 
			100, 108, -2, 117, -32, -60, -46, 91, -97, 41, -20, -12, -62, 44, 102, 46, 102, 33, 104, 69, 111, -61, 108, -9, -20, 15, -42, 67, -121, -97, 
			-2, -1, 125, 15, -14, 83, 80, -78, 0, 92, 39, 54, -45, 54, 25, 0, -24, 7, 0, 80, 74, 76, 16, -36, -60, 124, 65, -7, 17, 0, 
			51, 2, -79, 15, 85, 0, 48, 121, 115, 0, 109, 117, 2, -56, -44, -15, 111, -2, -128, 1, 60, 96, 1, 82, -60, 110, -35, 31, -92, -127, 
			90, 48, 8, 62, 67, 52, -112, 34, -79, 15, 76, -127, -22, -96, 87, -48, 1, -79, -65, 51, 35, 118, 116, -41, 97, 35, -80, 67, -72, 48, 
			-36, 1, -98, 14, 111, -125, -81, 33, 88, 17, -90, -120, 36, 68, 11, -30, 19, -110, 23, 121, 12, 89, -128, 28, 70, 17, 80, -122, -88, 84, 
			-44, 19, 98, -76, 25, -93, 51, -47, -93, 24, 102, -116, 7, -26, 38, 102, 27, -85, -119, -51, -62, 78, -31, 68, 113, 9, -72, 17, 60, 15, 
			-2, 4, -2, 57, 9, 31, 73, 60, -55, 107, -126, 20, 33, -117, -16, -125, 52, -126, 116, -115, -52, -101, 108, -127, -36, -109, 124, -98, -62, -101, 
			98, -115, 50, -124, 114, -113, 42, -119, -102, -100, -6, 50, -115, 48, 77, 59, -83, 41, -19, 28, 93, 12, 61, 53, 125, 29, -125, 62, -61, 2, 
			-29, 25, 38, 94, -90, 39, -52, 1, 44, 84, 44, -113, 89, 3, -39, -104, -40, 6, -40, -29, 57, -92, 57, 86, 56, -53, -72, -20, -71, 105, 
			-71, 71, 120, -46, 121, -115, -8, -16, 124, -49, -8, 83, 5, -116, 4, -55, 4, -121, -123, -14, -124, 109, 69, 88, 68, -34, -117, -34, 20, 11, 
			22, 87, -110, 96, -106, -60, 19, -93, -13, -117, -12, -126, -52, -116, -20, 91, -71, 55, -14, 19, 10, 51, -118, -13, 74, -21, -54, -33, 85, 81, 
			106, 76, -22, 66, 26, -38, -102, 78, 90, -47, -38, -71, 58, -115, -70, 99, 122, -69, 6, 120, -125, 93, -61, -9, 70, 47, -113, 116, 25, 55, 
			-102, 92, 55, 45, 50, -53, 52, 79, -79, 72, -74, -116, -77, -118, -78, -114, 56, 26, 106, 19, 108, 27, 98, 23, 102, 31, -27, 112, -62, 49, 
			-50, 41, -31, 88, -78, 115, -86, -53, 5, -41, 28, -73, 66, -9, 10, -113, 26, -49, 87, -34, -4, 62, 113, -66, -3, -2, 92, 1, 39, 3, 
			-57, -114, -53, 6, 23, -124, 108, -121, -39, -123, -73, 70, 114, 69, 101, 68, 111, -57, -72, -59, 14, -99, 84, 63, 85, -97, -64, 121, -70, 36, 
			-119, 38, 57, 51, -123, -28, 108, 122, 42, 125, -38, -75, 116, -119, -116, -10, 76, -93, -84, -119, -20, -32, -100, -17, 121, 25, 5, 28, -123, 13, 
			-59, -86, 37, -49, 47, 57, 95, 94, -69, 26, 83, -122, 41, -49, -67, -50, 86, 89, 93, 45, 86, -45, 81, -101, 82, 23, 127, -21, -28, -99, 
			-60, -58, -44, 123, 57, -9, -85, 90, -38, 30, -116, -73, 31, 116, 112, 116, -102, 117, -97, -19, -19, -20, -121, 63, -45, 27, -52, 126, -2, 97, 
			68, -7, 85, -50, -40, -10, -124, -27, -37, -26, 105, -74, -103, -108, -9, 11, -13, -70, -117, 21, -53, 123, -85, 122, -21, 23, 54, 6, -65, 28, 
			108, 49, -17, 40, -20, -86, -17, 43, 31, 72, -2, 58, 63, -104, -119, -71, -61, 3, -100, 5, 53, 96, 0, -84, 67, 20, -112, 52, 100, 3, 
			-59, 66, 87, -96, 110, 104, 5, 70, 14, -109, -125, 57, -61, -50, -63, 26, 97, 83, -60, -66, 93, 30, -18, 13, 47, -126, 63, -123, -17, 35, 
			-124, 17, -50, -120, 28, -60, 83, 36, 68, -20, -69, -61, -112, -11, -56, 21, -108, 0, -54, 23, 117, 3, -75, -114, -106, 70, -57, -96, -69, 48, 
			100, 24, 39, 76, 45, -26, 59, -42, 12, 91, -123, 61, -60, 57, -32, -102, -15, -76, -8, 40, -4, 27, 18, 117, -110, 74, 2, 57, 33, -114, 
			-80, 73, 26, 74, -70, 73, 22, 69, -10, -125, 60, -123, -126, -116, -94, -108, 82, -112, -78, -107, -22, 8, -43, 12, 117, 20, 13, 41, 77, 53, 
			-83, 22, -19, 44, 93, 34, 61, 15, 125, 31, 67, 32, 35, 45, 99, 7, -109, 63, 51, 61, 115, 31, 75, 12, -85, 48, -21, 59, -74, 60, 
			118, 19, 14, 44, 71, 47, -25, 25, 46, 93, 110, 28, -9, 11, -98, 92, 94, 39, 62, 1, -66, 109, -2, -57, 2, -71, -126, 94, 66, 10, 
			-62, 4, -31, 57, -111, 22, 98, -106, 10, 18, 55, -106, 80, -107, -108, -109, -110, -106, -106, -108, -111, -110, -107, -109, 83, -105, -41, 87, 56, -86, 
			-24, -89, 20, -93, -100, -93, 82, -85, -38, -81, -10, 65, 3, -93, 41, -86, 101, -91, 29, -89, 83, -87, 59, -86, 15, 55, 96, 55, 100, 55, 
			98, 62, -62, 97, -52, 101, -62, 79, -84, 86, -28, -51, -75, 45, -84, 45, -35, -84, 66, -84, -109, -113, -26, -39, 84, -37, -74, -37, -67, -76, 
			95, 118, -8, -31, 68, 117, 76, -64, 89, -43, -59, -46, -43, -37, 45, -42, 61, -33, -93, -38, 115, -56, 27, -29, -93, -23, 27, -23, -41, -32, 
			-65, 25, 40, 70, 60, 99, 90, 67, -96, 80, -3, -80, -36, -16, -87, 72, -15, -88, -28, -24, -41, 49, 18, -79, -25, -30, -106, 78, 105, -59, 
			-105, -99, -58, 37, -122, 37, 77, -99, -47, 79, 105, 60, -57, -103, -102, 115, 30, -101, 30, -97, -15, 53, -45, 63, 107, 46, -37, 45, 103, 60, 
			-49, 60, 127, -80, -48, -80, -24, 97, -119, 92, 105, -29, 101, -127, 43, 87, -81, -47, -107, 101, 84, 64, -41, 35, 42, 23, -86, 109, 106, -122, 
			107, 29, 110, -18, -42, 23, -33, 86, -69, -77, -44, -104, 117, 79, -79, 105, -75, -7, 74, -85, 69, 27, -66, -3, -39, -93, 51, -113, -115, -70, 
			40, -69, -89, 122, -21, -98, 36, 60, -75, 24, 16, 27, 66, 62, 95, 26, 30, 124, -39, 53, 58, 60, 62, 51, -15, 121, 18, 51, -51, 56, 
			35, -6, 94, -15, -125, -22, -126, -10, -110, -42, -118, -36, 42, -53, -38, -31, -89, -119, -49, -41, 55, -125, -73, -124, -73, 63, 124, 43, -36, 83, 
			-35, 127, 119, -112, -4, -53, -1, -92, -128, 15, -24, 0, 47, -112, 10, 110, -127, -41, 16, 28, 18, -125, -100, -95, 44, -94, -9, 119, 97, -30, 
			48, 63, 88, 21, 108, 1, -50, 7, -9, -121, -33, -127, -17, 33, 52, 17, 23, 16, 83, 72, 17, 100, 60, 113, -81, -13, -93, 18, 80, 111, 
			-48, -78, -24, 108, -12, 23, -116, 37, -26, 46, -106, 14, 123, 18, 59, -121, 51, -58, -35, -57, 115, -32, 51, -16, -5, 36, 126, 36, 111, 8, 
			-58, -124, 65, -46, 96, 50, 6, -78, 126, -14, 56, 10, 9, -118, 85, -54, 106, 42, 95, 106, 81, -22, 29, -102, 78, -38, 44, 58, 15, 122, 
			101, 6, 106, -122, 77, -58, 81, -90, 22, -26, 43, 44, 73, -84, 30, 108, -70, -20, -62, 28, -44, 28, 7, -100, 11, 92, -61, -36, -19, 60, 
			21, -68, -103, 124, 39, -8, 93, 5, 12, 4, 37, -123, 24, -124, -127, -16, -94, -56, -96, 104, -77, -40, 101, -15, 60, 98, 5, -70, 44, -51, 
			34, 99, 41, -101, 46, -9, 76, 1, -81, 104, -87, 84, -88, -68, -96, 42, -95, -106, -84, 62, -95, 41, -91, -107, -87, -67, -94, 107, -84, -41, 
			96, -64, 96, -104, 96, -76, 108, 108, 110, -46, 106, -58, 109, 126, -63, 98, -49, -54, -39, -70, -41, -122, -41, -10, -100, -35, -110, -125, -74, -29, 
			101, -89, 109, 103, 99, -105, 50, -41, -81, -18, 70, 30, -105, -67, 32, -17, 83, 62, 95, -4, 124, -4, 23, 2, -99, -125, 102, -125, 61, 66, 
			-26, -61, 124, -62, -105, 35, 67, -93, -10, 79, 36, -57, -110, -60, -107, -98, -30, -115, 111, 56, -83, -105, 56, -107, 28, -106, -126, 60, 123, -4, 
			-36, 124, -102, -43, -7, -127, 12, -27, 11, 117, 89, -84, 23, 51, 115, 64, -82, 111, -34, 104, -127, 90, -31, -11, 98, 68, -119, 87, 105, -33, 
			101, -50, 43, 39, -81, -114, -106, 9, -105, -97, -82, 120, 85, -55, 83, 21, 81, -35, 123, -125, -85, -74, -88, -114, -73, -2, -34, 109, -3, 59, 
			-29, -115, -98, 119, 63, 54, -123, -33, -33, 106, 9, 110, -35, 104, -13, 108, 31, 123, -92, -43, 113, -65, -109, -83, 43, -75, -5, 83, -81, 121, 
			95, 107, 63, -25, -45, -72, 103, 115, -125, -38, 67, 101, 47, 96, -61, 94, 35, -49, 95, -119, -116, 102, -115, 125, 122, 109, 52, 81, -1, 22, 
			61, -23, 51, -11, -14, -99, -36, 76, -58, 123, -8, -100, -61, -121, 7, 11, -4, -117, 5, 75, 107, 43, 10, 31, -67, 87, 19, -41, -50, -82, 
			39, 127, 10, -34, 48, -5, -52, -10, 121, -2, 75, -10, -90, -38, -26, -24, -41, 99, 95, -105, -74, -36, -73, -70, -74, -71, -73, -109, -73, 63, 
			-20, 8, -18, -72, -18, 20, -18, 12, 125, 67, 126, 83, -6, 22, -6, -83, -26, -37, -30, -82, -14, 110, -42, -18, -4, -98, -30, -34, -123, -3, 
			-56, -17, 105, 63, -3, 31, -26, 37, -11, -5, 2, 22, 34, -47, 4, 0, 57, 125, 120, -72, -55, 11, 0, 58, 27, -128, -125, -117, -121, -121, 
			-5, 101, -121, -121, 7, -27, -60, 34, 115, 18, -128, -114, -128, -33, 119, -21, -65, 114, 13, 57, 49, -65, 100, -1, -69, 59, -46, -1, 0, -47, 
			122, 88, -39, -114, 29, -45, 24, 0, 0, 0, 9, 112, 72, 89, 115, 0, 0, 11, 19, 0, 0, 11, 19, 1, 0, -102, -100, 24, 0, 0, 
			1, 92, 73, 68, 65, 84, 40, -111, 53, -50, -79, 110, 27, 81, 12, 68, -47, 25, -110, 111, 87, 107, -59, -112, -111, 38, 101, -102, -76, -7, 
			-1, -49, -118, 3, -40, -46, -22, 61, 114, -104, 34, 73, 127, -127, 115, -7, -3, -57, -49, 115, 62, -112, 79, -27, -68, 92, 111, -57, -15, 18, 
			17, -17, -65, -33, -17, -97, 31, -48, 28, -122, 38, 9, 84, 21, -44, 2, -126, 12, -107, 32, 17, 116, 119, -46, -26, -100, -49, 115, -46, -24, 
			-12, -42, 114, 31, 36, 1, 8, 5, -107, 53, 12, 80, 85, -127, 116, 119, 51, -50, -71, 74, -71, -115, 49, -58, 102, -26, 85, 37, 73, 42, 
			0, 104, -60, -102, -97, 80, -83, -46, -15, -14, 10, -13, -22, -66, 63, 30, -24, 21, 102, 85, 18, -104, -103, 64, 1, 0, 84, -86, 104, 40, 
			43, -73, 49, 98, 12, 51, -53, -52, -84, 4, -32, 102, 107, -83, -25, 124, 66, -3, -73, -2, 39, -104, -119, -32, -27, 56, -114, -29, 0, -19, 
			124, -100, 43, -45, -95, -57, -7, 56, -49, -77, -43, -86, -22, -82, -86, -114, 48, -125, -123, 33, -125, 118, -67, 124, 49, 94, 82, -107, 43, 93, 
			106, -44, 121, 78, 8, 52, 58, -68, -118, 17, 109, 70, 0, -111, 51, -113, -53, -11, 120, -71, -83, -22, -86, -91, -46, 112, -21, 50, 17, 112, 
			0, 104, 107, -128, 0, -70, 107, -83, 52, 84, -36, -66, 126, -37, -113, 43, 98, -72, -103, -121, 123, 68, 108, 99, -116, -31, -18, 0, 72, -102, 
			1, -128, -44, -18, 30, -37, -66, 31, -81, 111, -96, 119, -81, -79, -17, -5, 115, -45, 60, 27, 6, -128, 6, 7, -85, 5, -63, 28, 0, -58, 
			54, -94, 61, 24, -57, 8, -49, 95, -17, -44, 106, 117, 67, -103, -94, -63, 44, 96, -96, 36, -118, 100, 59, -128, -114, -71, -26, -66, -17, -31, 
			-106, -103, -49, -5, 7, -75, -86, 84, 45, 111, -2, -1, 49, -110, -35, 13, 116, -91, -2, 0, -9, 103, -17, -111, 114, -68, -95, 51, 0, 0, 
			0, 0, 73, 69, 78, 68, -82, 66, 96, -126 };
	final Cprws cprws = new Cprws();
	final CprwsSEI port = cprws.getCprwsPort();
	
	@Test(expectedExceptions = Exception.class)
	public void _01testAddIdCardPrintEventBadPrincipal() throws Exception {
		ServiceReturn idCardServiceReturn = port.addIdCardPrintEvent(null,
				ServiceAuthentication.GOOD_PASSWORD,  "llg5", "person_id", "100003","llg5",
				"128.118.88.4", "myWorkState");
		if (idCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(idCardServiceReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions = Exception.class)
	public void _02testAddIdCardPrintEventBadPassword() throws Exception {
		ServiceReturn idCardServiceReturn = port.addIdCardPrintEvent(ServiceAuthentication.GOOD_USERID,
				"",  "llg5", "person_id", "100003","llg5",
				"128.118.88.4", "myWorkState");
		if (idCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(idCardServiceReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions = Exception.class)
	public void _03testAddIdCardPrintEventBadRequestedBy() throws Exception {
		ServiceReturn idCardServiceReturn = port.addIdCardPrintEvent(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD,  " ", "person_id", "100003","llg5",
				"128.118.88.4", "myWorkState");
		if (idCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(idCardServiceReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions = Exception.class)
	public void _04testAddIdCardPrintEventIdentifierType() throws Exception {
		ServiceReturn idCardServiceReturn = port.addIdCardPrintEvent(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD,  " llg5", "", "100003","llg5",
				"128.118.88.4", "myWorkState");
		if (idCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(idCardServiceReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions = Exception.class)
	public void _05testAddIdCardPrintEventIdentifier() throws Exception {
		ServiceReturn idCardServiceReturn = port.addIdCardPrintEvent(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD,  "llg5", "id_card", "","llg5",
				"128.118.88.4", "myWorkState");
		if (idCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(idCardServiceReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions = Exception.class)
	public void _06testAddIdCardPrintEventNotIdCardType() throws Exception {
		ServiceReturn idCardServiceReturn = port.addIdCardPrintEvent(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD,  "llg5", "person_id", "100004","llg5",
				"128.118.88.4", "myWorkState");
		if (idCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(idCardServiceReturn.getStatusMessage());
		}
	}
	@Test
	public void _07() throws Exception {
		/* if no id card record exist add it */
		IdCardServiceReturn idCardServiceReturn = port.getIdCard(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD,  "llg5", "id_card", "1234567890123456", null, null);
		if (idCardServiceReturn.getStatusCode() != 0) {
			ServiceReturn addIdCardServiceReturn = port.addIdCard(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD,  "llg5", "person_id", "100004", "ID_CARD_ID_PLUS_CARD_STUDENT","1234567890123456",null, testImage,
			"7/3/2012");
			if (addIdCardServiceReturn.getStatusCode() !=0) {
				throw new Exception("No IdCard number");
			}
		}
		ServiceReturn IdCardServiceReturn = port.addIdCardPrintEvent(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD,  "llg5", "id_card", "1234567890123456","llg5",
				"128.118.88.4", "myWorkState");
		if (IdCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(IdCardServiceReturn.getStatusMessage());
		}
		
	}
	
	@Test(expectedExceptions = Exception.class)
	public void _08() throws Exception {
		IdCardPrintEventServiceReturn IdCardServiceReturn = port.getIdCardPrintEvent(null,
				ServiceAuthentication.GOOD_PASSWORD,  "llg5", "person_id", "100003");
		if (IdCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(IdCardServiceReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions = Exception.class)
	public void _081() throws Exception {
		IdCardPrintEventServiceReturn IdCardServiceReturn = port.getIdCardPrintEvent(ServiceAuthentication.GOOD_USERID,
				"",  "llg5", "person_id", "100003");
		if (IdCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(IdCardServiceReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions = Exception.class)
	public void _09testGetIdCardPrintEventBadRequestedBy() throws Exception {
		IdCardPrintEventServiceReturn IdCardServiceReturn = port.getIdCardPrintEvent(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD,  " ", "person_id", "100003");
		if (IdCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(IdCardServiceReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions = Exception.class)
	public void _10testGetIdCardPrintEventIdentifierType() throws Exception {
		IdCardPrintEventServiceReturn idCardServiceReturn = port.getIdCardPrintEvent(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD,  " llg5", "", "100003");
		if (idCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(idCardServiceReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions = Exception.class)
	public void _11testGetIdCardPrintEventIdentifier() throws Exception {
		IdCardPrintEventServiceReturn idCardServiceReturn = port.getIdCardPrintEvent(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD,  "llg5", "id_card", "");
		if (idCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(idCardServiceReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions = Exception.class)
	public void _12testGetIdCardPrintEventNotIdCardType() throws Exception {
		IdCardPrintEventServiceReturn idCardServiceReturn = port.getIdCardPrintEvent(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD,  "llg5", "person_id", "100004");
		if (idCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(idCardServiceReturn.getStatusMessage());
		}
	}
	@Test
	public void _13testGetIdCardPrintEventIdentfierMustBeIdCard() throws Exception {
		/* if no id card record exist add it */
		IdCardServiceReturn idCardServiceReturn = port.getIdCard(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD,  "llg5", "id_card", "1234567890123456", null, null);
		if (idCardServiceReturn.getStatusCode() != 0) {
			ServiceReturn addIdCardServiceReturn = port.addIdCard(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD,  "llg5", "person_id", "100004", "Id_card_student","1234567890123456",null, testImage,
			"7/3/2012");
			if (addIdCardServiceReturn.getStatusCode() !=0) {
				throw new Exception("No IdCard number");
			}
		}
		IdCardPrintEventServiceReturn IdCardPrintEventServiceReturn = port.getIdCardPrintEvent(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD,  "llg5", "id_card", "1234567890123456");
		if (IdCardPrintEventServiceReturn.getStatusCode() != 0) {
			throw new Exception(IdCardPrintEventServiceReturn.getStatusMessage());
		}
		
	}
}