package com.softway.diagnosis.bootstrap;

import com.softway.diagnosis.application.port.input.medicalunit.NoSuchMedicalUnitForThesePathologiesException;
import com.softway.diagnosis.application.port.input.pathology.PathologyNotFoundException;
import com.softway.diagnosis.domain.medicalunit.MedicalUnit;
import com.softway.diagnosis.domain.pathology.HealthIndex;
import com.softway.diagnosis.infrastructure.adapter.input.console.DiagnosisConsoleApplication;

import java.util.Collection;

/**
 * The DiagnosisLauncher class serves as the entry point for the console-based diagnosis application.
 * It accepts a single argument representing a health index, diagnoses associated pathologies, and identifies
 * relevant medical units for treatment.
 * <p>
 * Responsibilities:
 * - Parses the health index provided as a command-line argument.
 * - Utilizes the DiagnosisConsoleApplication to diagnose pathologies.
 * - Retrieves and displays medical units capable of treating the diagnosed pathologies.
 * <p>
 * Error Handling:
 * - Ensures that the input argument is a valid integer, otherwise prints an error message and exits.
 * - Handles domain-specific exceptions such as:
 *   - {@code PathologyNotFoundException} when no pathologies are found for the provided health index.
 *   - {@code NoSuchMedicalUnitForThesePathologiesException} when no medical units exist for the diagnosed pathologies.
 * - Wraps domain exceptions into runtime exceptions for unexpected failures.
 * <p>
 * Command-line Usage:
 * - Expects exactly one argument: {@code Usage: DiagnosisLauncher <health index>}.
 * <p>
 * Preconditions:
 * - The health index must be a valid integer and within the scope of the domain model.
 * - A properly configured instance of {@code DiagnosisConsoleApplication} must exist.
 * <p>
 * Postconditions:
 * - Diagnosed pathologies and corresponding medical units are printed to the console if successful.
 * - Exits the application with an error message if any validation or processing fails.
 */
public class DiagnosisLauncher {

    public static void main(final String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: DiagnosisLauncher <health index>");
            System.exit(1);
        }

        try {
            final HealthIndex healthIndex = new HealthIndex(Integer.parseInt(args[0]));
            final DiagnosisConsoleApplication diagnosisConsoleApplication = new DiagnosisConsoleApplication();
            final Collection<MedicalUnit> foundMedicalUnits = diagnosisConsoleApplication.findMedicalUnitsByHealthIndex(healthIndex);
            System.out.println(DiagnosisConsoleApplication.toString(foundMedicalUnits));
        } catch (final NumberFormatException e) {
            System.err.println("Health index must be an integer: " + args[0]);
            System.exit(1);
        } catch (final PathologyNotFoundException e) {
            throw new RuntimeException("Error while diagnosing pathology", e);
        } catch (final NoSuchMedicalUnitForThesePathologiesException e) {
            throw new RuntimeException("Error while finding a medical unit", e);
        }
    }
}